package com.openclassrooms.Project6.Services;

import com.openclassrooms.Project6.Models.*;
import com.openclassrooms.Project6.Repositories.AccountRepository;
import com.openclassrooms.Project6.Repositories.ConnectionRepository;
import com.openclassrooms.Project6.Repositories.IbanRepository;
import com.openclassrooms.Project6.Repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {


    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ConnectionRepository connectionRepository;

    @Autowired
    private IbanRepository ibanRepository;


    public void createRegularTransaction(String senderUserEmail, String receiverUserEmail, float moneyAmountVariation) {

        Account senderAccount = accountRepository.findAccountByUserEmail(senderUserEmail);

        Account receiverAccount = accountRepository.findAccountByUserEmail(receiverUserEmail);

        if(senderAccount.getAccountStatus().getAccountStatus().equals("Active")
                && receiverAccount.getAccountStatus().getAccountStatus().equals("Active")
                && (senderAccount.getCurrentBalance() - moneyAmountVariation) >= 0) {

            float sendersBalanceBeforeTransaction = senderAccount.getCurrentBalance();
            senderAccount.setCurrentBalance(sendersBalanceBeforeTransaction - moneyAmountVariation);
            accountRepository.save(senderAccount);


            float receiversBalanceBeforeTransaction = receiverAccount.getCurrentBalance();
            receiverAccount.setCurrentBalance(receiversBalanceBeforeTransaction + moneyAmountVariation);
            accountRepository.save(receiverAccount);

            String transactionTypeString = "Regular";
            TransactionType transactionType = new TransactionType(transactionTypeString);

            Connection newConnection = connectionRepository.findConnectionByUserEmail(receiverUserEmail);

            Date madeAt = new Date();

            Transaction newTransaction = new Transaction(transactionType, senderAccount, moneyAmountVariation, madeAt);
            newTransaction.setConnection(newConnection);
            newTransaction.setSendersBalanceBeforeTransaction(sendersBalanceBeforeTransaction);
            newTransaction.setReceiversBalanceBeforeTransaction(receiversBalanceBeforeTransaction);
            transactionRepository.save(newTransaction);
        }
    }


    public void createTopUpTransaction(String userEmail, float moneyAmountVariation, String origin) {

        Account account = accountRepository.findAccountByUserEmail(userEmail);

        if(account.getAccountStatus().getAccountStatus().equals("Active")) {

            float balanceBeforeTransaction = account.getCurrentBalance();
            account.setCurrentBalance(balanceBeforeTransaction + moneyAmountVariation);
            accountRepository.save(account);

            String transactionTypeString = "TopUp";
            TransactionType transactionType = new TransactionType(transactionTypeString);

            Date madeAt = new Date();

            Transaction newTransaction = new Transaction(transactionType, account, moneyAmountVariation, madeAt);
            newTransaction.setSendersBalanceBeforeTransaction(balanceBeforeTransaction);
            newTransaction.setOrigin(origin);
            transactionRepository.save(newTransaction);
        }
    }


    public void createWithdrawalTransaction(String userEmail, String ibanString, float moneyAmountVariation) {

        Account account = accountRepository.findAccountByUserEmail(userEmail);

        if(account.getAccountStatus().getAccountStatus().equals("Active")
                && (account.getCurrentBalance() - moneyAmountVariation) >= 0) {

            float balanceBeforeTransaction = account.getCurrentBalance();
            account.setCurrentBalance(balanceBeforeTransaction - moneyAmountVariation);
            accountRepository.save(account);

            String transactionTypeString = "Withdrawal";
            TransactionType transactionType = new TransactionType(transactionTypeString);

            Date madeAt = new Date();

            if(ibanRepository.findByAccount_UserEmail(userEmail).stream().noneMatch(i -> i.getIban().equals(ibanString))) {

                Iban newIban = new Iban(account, ibanString);

                ibanRepository.save(newIban);
            }

            Transaction newTransaction = new Transaction(transactionType, account, moneyAmountVariation, madeAt);
            newTransaction.setIban(new Iban(account, ibanString));
            newTransaction.setSendersBalanceBeforeTransaction(balanceBeforeTransaction);
            transactionRepository.save(newTransaction);
        }
    }


    public void createCancellationTransaction (Transaction transactionToCancel) {

        Account senderAtOriginAccount = accountRepository.findAccountByUserEmail(transactionToCancel.getAccount().getUser().getEmail());
        Account receiverAtOriginAccount = accountRepository.findAccountByUserEmail(transactionToCancel.getConnection().getUser().getEmail());
        float moneyAmountVariation = transactionToCancel.getMoneyAmountVariation();

        if(senderAtOriginAccount.getAccountStatus().getAccountStatus().equals("Active")
                && receiverAtOriginAccount.getAccountStatus().getAccountStatus().equals("Active")
                && (receiverAtOriginAccount.getCurrentBalance() - moneyAmountVariation) >= 0) {

            float receiverAtOriginsBalanceBeforeTransaction = receiverAtOriginAccount.getCurrentBalance();
            receiverAtOriginAccount.setCurrentBalance(receiverAtOriginsBalanceBeforeTransaction - moneyAmountVariation);
            accountRepository.save(receiverAtOriginAccount);


            float senderAtOriginsBalanceBeforeTransaction = senderAtOriginAccount.getCurrentBalance();
            senderAtOriginAccount.setCurrentBalance(senderAtOriginsBalanceBeforeTransaction + moneyAmountVariation);
            accountRepository.save(senderAtOriginAccount);

            String transactionTypeString = "Cancellation";
            TransactionType transactionType = new TransactionType(transactionTypeString);

            Connection newConnection = connectionRepository.findConnectionByUserEmail(senderAtOriginAccount.getUser().getEmail());

            Date madeAt = new Date();

            Transaction newTransaction = new Transaction(transactionType, receiverAtOriginAccount, moneyAmountVariation, madeAt);
            newTransaction.setConnection(newConnection);
            newTransaction.setSendersBalanceBeforeTransaction(receiverAtOriginsBalanceBeforeTransaction);
            newTransaction.setReceiversBalanceBeforeTransaction(senderAtOriginsBalanceBeforeTransaction);
            transactionRepository.save(newTransaction);
        }
    }


    public Transaction getTransactionById(int transactionId) {

        return transactionRepository.findById(transactionId).get();
    }


    public List<Transaction> getAllRegularTransactionsAsSenderByUserEmail(String userEmail) {

        List<Transaction> newList = transactionRepository.findTransactionsByAccountUserEmail(userEmail).stream()
                                        .filter(t -> t.getTransactionType().getTransactionType().equals("Regular"))
                                        .collect(Collectors.toList());

        return newList;
    }


    public List<Transaction> getAllRegularTransactionsAsReceiverByUserEmail(String userEmail) {

        List<Transaction> newList = transactionRepository.findTransactionsByConnectionUserEmail(userEmail).stream()
                                        .filter(t -> t.getTransactionType().getTransactionType().equals("Regular"))
                                        .collect(Collectors.toList());

        return newList;
    }


    public List<Transaction> getAllRegularTransactionsByUserEmail(String userEmail) {

        List<Transaction> newList = new ArrayList<>();

        newList.addAll(getAllRegularTransactionsAsSenderByUserEmail(userEmail));
        newList.addAll(getAllRegularTransactionsAsReceiverByUserEmail(userEmail));

        return newList;
    }


    public List<Transaction> getAllTopUpTransactionsByUserEmail(String userEmail) {

        List<Transaction> newList = transactionRepository.findTransactionsByAccountUserEmail(userEmail).stream()
                                        .filter(t -> t.getTransactionType().getTransactionType().equals("TopUp"))
                                        .collect(Collectors.toList());

        return newList;
    }


    public List<Transaction> getAllWithdrawalTransactionsByUserEmail(String userEmail) {

        List<Transaction> newList = transactionRepository.findTransactionsByAccountUserEmail(userEmail).stream()
                                        .filter(t -> t.getTransactionType().getTransactionType().equals("Withdrawal"))
                                        .collect(Collectors.toList());

        return newList;
    }


    public List<Transaction> getAllTopUpAndWithdrawalTransactionsByUserEmail(String userEmail) {

        List<Transaction> newList = new ArrayList<>();

        newList.addAll(getAllTopUpTransactionsByUserEmail(userEmail));
        newList.addAll(getAllWithdrawalTransactionsByUserEmail(userEmail));

        return newList;
    }


    public List<Transaction> getAllCancellationTransactionsAsSenderByUserEmail(String userEmail) {

        List<Transaction> newList = transactionRepository.findTransactionsByAccountUserEmail(userEmail).stream()
                .filter(t -> t.getTransactionType().getTransactionType().equals("Cancellation"))
                .collect(Collectors.toList());

        return newList;
    }


    public List<Transaction> getAllCancellationTransactionsAsReceiverByUserEmail(String userEmail) {

        List<Transaction> newList = transactionRepository.findTransactionsByConnectionUserEmail(userEmail).stream()
                .filter(t -> t.getTransactionType().getTransactionType().equals("Cancellation"))
                .collect(Collectors.toList());

        return newList;
    }


    public List<Transaction> getAllCancellationTransactionsByUserEmail(String userEmail) {

        List<Transaction> newList = new ArrayList<>();

        newList.addAll(getAllCancellationTransactionsAsSenderByUserEmail(userEmail));
        newList.addAll(getAllCancellationTransactionsAsReceiverByUserEmail(userEmail));

        return newList;
    }


    public List<Transaction> getAllUserTransactionsByUserEmail(String userEmail) {

        List<Transaction> newList = new ArrayList<>();

        newList.addAll(getAllRegularTransactionsAsSenderByUserEmail(userEmail));
        newList.addAll(getAllRegularTransactionsAsReceiverByUserEmail(userEmail));
        newList.addAll(getAllTopUpTransactionsByUserEmail(userEmail));
        newList.addAll(getAllWithdrawalTransactionsByUserEmail(userEmail));
        newList.addAll(getAllCancellationTransactionsAsSenderByUserEmail(userEmail));
        newList.addAll(getAllCancellationTransactionsAsReceiverByUserEmail(userEmail));

        return newList;
    }


    public List<Transaction> getAllTransactionsByAccountAndTransactionTypes(String accountType, String transactionType) {

        List<Transaction> filteredList = new ArrayList<>();

        if((accountType.equals("Regular") || accountType.equals("Company"))

                && (transactionType.equals("Regular") || transactionType.equals("TopUp")
                || transactionType.equals("Withdrawal")
                || transactionType.equals("Cancellation"))) {

            List<Transaction> filteredByAccountType = transactionRepository.findAll().stream()
                                                        .filter(t -> t.getAccount().getAccountType().getAccountType()
                                                        .equals(accountType)).collect(Collectors.toList());

            filteredList = filteredByAccountType.stream().filter(t -> t.getTransactionType().getTransactionType()
                                                        .equals(transactionType)).collect(Collectors.toList());
        }

        return filteredList;
    }


    public List<Transaction> getAllRegularAccountsTransactions() {

        return transactionRepository.findAll().stream()
                .filter(t -> t.getAccount().getAccountType().getAccountType().equals("Regular"))
                .collect(Collectors.toList());
    }


    /*public List<Transaction> getAllRegularAccountsRegularTransactions() {

        return getAllRegularAccountsTransactions().stream()
                .filter(t -> t.getTransactionType().getTransactionType().equals("Regular"))
                .collect(Collectors.toList());
    }


    public List<Transaction> getAllRegularAccountsTopUpTransactions() {

        return getAllRegularAccountsTransactions().stream()
                .filter(t -> t.getTransactionType().getTransactionType().equals("TopUp"))
                .collect(Collectors.toList());
    }


    public List<Transaction> getAllRegularAccountsWithdrawalTransactions() {

        return getAllRegularAccountsTransactions().stream()
                .filter(t -> t.getTransactionType().getTransactionType().equals("Withdrawal"))
                .collect(Collectors.toList());
    }


    public List<Transaction> getAllRegularAccountsCancellationTransactions() {

        return getAllRegularAccountsTransactions().stream()
                .filter(t -> t.getTransactionType().getTransactionType().equals("Cancellation"))
                .collect(Collectors.toList());
    }*/
}
