package com.openclassrooms.Project6.Services;

import com.openclassrooms.Project6.Models.*;
import com.openclassrooms.Project6.Repositories.AccountRepository;
import com.openclassrooms.Project6.Repositories.ConnectionRepository;
import com.openclassrooms.Project6.Repositories.IbanRepository;
import com.openclassrooms.Project6.Repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
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

    //SET COMPANY'S ACCOUNT EMAIL!!!
    String companysEmail;

    //FEE SET TO 0.5% AS FLOAT!!!
    float feeCostInPercentage = 0.5f;
    float feeCalculationFactor = feeCostInPercentage/100;


    /*public void createRegularTransaction(String senderUserEmail, String receiverUserEmail, float moneyAmount) {

        Account senderAccount = accountRepository.findAccountByUserEmail(senderUserEmail);

        Account receiverAccount = accountRepository.findAccountByUserEmail(receiverUserEmail);

        if(senderAccount.getAccountStatus().getAccountStatus().equals("Active")
                && receiverAccount.getAccountStatus().getAccountStatus().equals("Active")
                && (senderAccount.getCurrentBalance() - moneyAmount) >= 0) {

            float sendersBalanceBeforeTransaction = senderAccount.getCurrentBalance();
            senderAccount.setCurrentBalance(sendersBalanceBeforeTransaction - moneyAmount);
            accountRepository.save(senderAccount);


            float receiversBalanceBeforeTransaction = receiverAccount.getCurrentBalance();
            receiverAccount.setCurrentBalance(receiversBalanceBeforeTransaction + moneyAmount);
            accountRepository.save(receiverAccount);

           *//* String transactionTypeString = "Regular";
            TransactionType transactionType = new TransactionType(transactionTypeString);*//*

            Connection newConnection = connectionRepository.findConnectionByUserEmail(receiverUserEmail);

            *//*Date madeAt = new Date();*//*

            *//*Transaction newTransaction = new Transaction(transactionType, senderAccount, moneyAmount, madeAt);*//*
            newTransaction.setConnection(newConnection);
            newTransaction.setSendersBalanceBeforeTransaction(sendersBalanceBeforeTransaction);
            newTransaction.setReceiversBalanceBeforeTransaction(receiversBalanceBeforeTransaction);
            transactionRepository.save(newTransaction);
        }
    }

    // cambiar nombre del float y sacar origin
    public void createTopUpTransaction(String userEmail, float moneyAmount, String origin) {

        Account account = accountRepository.findAccountByUserEmail(userEmail);

        if(account.getAccountStatus().getAccountStatus().equals("Active")) {

            float balanceBeforeTransaction = account.getCurrentBalance();
            account.setCurrentBalance(balanceBeforeTransaction + moneyAmount);
            accountRepository.save(account);

           *//* String transactionTypeString = "TopUp";
            TransactionType transactionType = new TransactionType(transactionTypeString);

            Date madeAt = new Date();

            Transaction newTransaction = new Transaction(transactionType, account, moneyAmount, madeAt);*//*
            newTransaction.setSendersBalanceBeforeTransaction(balanceBeforeTransaction);
            newTransaction.setOrigin(origin);
            transactionRepository.save(newTransaction);
        }
    }


    public void createWithdrawalTransaction(String userEmail, String ibanString, float moneyAmount) {

        Account account = accountRepository.findAccountByUserEmail(userEmail);

        if(account.getAccountStatus().getAccountStatus().equals("Active")
                && (account.getCurrentBalance() - moneyAmount) >= 0) {

            float balanceBeforeTransaction = account.getCurrentBalance();
            account.setCurrentBalance(balanceBeforeTransaction - moneyAmount);
            accountRepository.save(account);

            *//*String transactionTypeString = "Withdrawal";
            TransactionType transactionType = new TransactionType(transactionTypeString);

            Date madeAt = new Date();*//*

            if(ibanRepository.findByAccount_UserEmail(userEmail).stream().noneMatch(i -> i.getIban().equals(ibanString))) {

                Iban newIban = new Iban(account, ibanString);

                ibanRepository.save(newIban);
            }

            *//*Transaction newTransaction = new Transaction(transactionType, account, moneyAmount, madeAt);*//*
            newTransaction.setIban(new Iban(account, ibanString));
            newTransaction.setSendersBalanceBeforeTransaction(balanceBeforeTransaction);
            transactionRepository.save(newTransaction);
        }
    }*/

    public void createTransactionByTransactionType(String transactionTypeString, String sendersEmail,
                                                   float moneyAmount, String receiversEmailOrIbanOrOrigin) {

        if(transactionConditionsValidator(transactionTypeString, sendersEmail, moneyAmount,
                receiversEmailOrIbanOrOrigin)){

            Account sendersAccount = accountRepository.findAccountByUserEmail(sendersEmail);
            float sendersBalanceBeforeTransaction = sendersAccount.getCurrentBalance();

            Date madeAt = new Date();

            TransactionType transactionType = new TransactionType(transactionTypeString);

            Transaction newTransaction = new Transaction(transactionType, sendersAccount, moneyAmount, madeAt);
            newTransaction.setSendersBalanceBeforeTransaction(sendersBalanceBeforeTransaction);

            if(!transactionTypeString.equals("TopUp")) {

                sendersAccount.setCurrentBalance(sendersBalanceBeforeTransaction + moneyAmount);
                accountRepository.save(sendersAccount);
            }

            if(transactionTypeString.equals("Regular")){

                Account receiversAccount = accountRepository.findAccountByUserEmail(receiversEmailOrIbanOrOrigin);
                float receiversBalanceBeforeTransaction = receiversAccount.getCurrentBalance();

                Account companysAccount = accountRepository.findAccountByUserEmail(companysEmail);
                float companysBalanceBeforeTransaction = companysAccount.getCurrentBalance();

                receiversAccount.setCurrentBalance(receiversBalanceBeforeTransaction + moneyAmount);
                accountRepository.save(receiversAccount);

                sendersAccount.setCurrentBalance(sendersBalanceBeforeTransaction
                        - (moneyAmount * (1 + feeCalculationFactor)));
                accountRepository.save(sendersAccount);

                companysAccount.setCurrentBalance(companysBalanceBeforeTransaction
                        + (moneyAmount * feeCalculationFactor));
                accountRepository.save(companysAccount);

                Connection newConnection = connectionRepository.findConnectionByUserEmail(receiversEmailOrIbanOrOrigin);
                newTransaction.setConnection(newConnection);
                newTransaction.setReceiversBalanceBeforeTransaction(receiversBalanceBeforeTransaction);

            }else if(transactionTypeString.equals("TopUp")) {

                sendersAccount.setCurrentBalance(sendersBalanceBeforeTransaction + moneyAmount);
                accountRepository.save(sendersAccount);

                newTransaction.setOrigin(receiversEmailOrIbanOrOrigin);

            }else if(transactionTypeString.equals("Withdrawal")) {

                newTransaction.setIban(new Iban(sendersAccount, receiversEmailOrIbanOrOrigin));

                if(ibanRepository.findByAccount_UserEmail(sendersEmail).stream()
                        .noneMatch(i -> i.getIban().equals(receiversEmailOrIbanOrOrigin))) {

                    Iban newIban = new Iban(sendersAccount, receiversEmailOrIbanOrOrigin);

                    ibanRepository.save(newIban);
                }
            }
            transactionRepository.save(newTransaction);
        }
    }


    /*public Transaction getTransactionById(int transactionId) {

        return transactionRepository.findById(transactionId).get();
    }

    public List<Transaction> getAllTransactions() {

        return transactionRepository.findAll();
    }*/


    //CORRECT TO BE ABLE TO GET ALL TRANSACTIONS

    //Added "Receiver" as Transaction Type to show Transactions received by User
    public List<Transaction> getAUsersTransactionsByEmailAndTransactionType(String userEmail, String transactionType) {

        List<Transaction> list = new ArrayList<>();

        if((transactionTypeValidator(transactionType) || transactionType.equals("Receiver"))
            && activeAccountValidator(userEmail)) {

            if(!transactionType.equals("Receiver")) {

                list = filterTransactionsByTransactionType(transactionType).stream().filter(t -> t.getAccount()
                        .getUser().getEmail().equals(userEmail)).collect(Collectors.toList());

                if(transactionType.equals("All")) {

                    list.addAll(transactionRepository.findTransactionsByConnectionUserEmail(userEmail));
                }

            }else{

                list = transactionRepository.findTransactionsByConnectionUserEmail(userEmail);
            }
        }

        return list;
    }


    /*public List<Transaction> getAllRegularTransactionsAsSenderByUserEmail(String userEmail) {

        List<Transaction> newList = transactionRepository.findTransactionsByAccountUserEmail(userEmail).stream()
                                        .filter(t -> t.getTransactionType().getTransactionType().equals("Regular"))
                                        .collect(Collectors.toList());

        return newList;
    }


    public List<Transaction> getAllRegularTransactionsAsReceiverByUserEmail(String userEmail) {

        List<Transaction> newList = transactionRepository.findTransactionsByConnectionUserEmail(userEmail);

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


    public List<Transaction> getAllUserTransactionsByUserEmail(String userEmail) {

        List<Transaction> newList = new ArrayList<>();

        newList.addAll(getAllRegularTransactionsAsSenderByUserEmail(userEmail));
        newList.addAll(getAllRegularTransactionsAsReceiverByUserEmail(userEmail));
        newList.addAll(getAllTopUpTransactionsByUserEmail(userEmail));
        newList.addAll(getAllWithdrawalTransactionsByUserEmail(userEmail));

        return newList;
    }*/


    /*public List<Transaction> getAllTransactionsByAccountAndTransactionType(String accountType,
                                                                           String transactionType) {

        List<Transaction> newList = new ArrayList<>();

        if(accountTypeValidator(accountType) && transactionTypeValidator(transactionType)) {

            List<Transaction> filteredList = filterTransactionsByTransactionType(transactionType);

            newList = filteredList.stream().filter(t -> t.getAccount().getAccountType().getAccountType()
                        .equals(accountType)).collect(Collectors.toList());
        }

        return newList;
    }*/


    public boolean transactionConditionsValidator(String transactionTypeString, String sendersEmail,
                                                  float moneyAmount, String receiversEmailOrIbanOrOrigin) {
        boolean value = false;

        if(transactionTypeValidator(transactionTypeString) && activeAccountValidator(sendersEmail)) {

            float balanceAvailableMinusTransaction = (accountRepository.findAccountByUserEmail(sendersEmail)
                    .getCurrentBalance() - moneyAmount);

            if(transactionTypeString.equals("TopUp")) {

                value = true;

            }else{

                if(balanceAvailableMinusTransaction >= 0) {

                    if(transactionTypeString.equals("Withdrawal")) {

                        value = true;

                    }else{

                        if(activeAccountValidator(receiversEmailOrIbanOrOrigin)
                            && (balanceAvailableMinusTransaction - (moneyAmount*feeCalculationFactor)) >= 0) {

                            value = true;
                        }
                    }
                }
            }
        }
        return value;
    }

    //CHECK IF I NEED IT
    public boolean accountTypeValidator(String accountType) {

        boolean value = false;

        if(Arrays.asList("Regular", "Company").contains(accountType)) {

            value = true;
        }

        return value;
    }


    public boolean transactionTypeValidator(String transactionType) {

        boolean value = false;

        if(Arrays.asList("Regular", "TopUp", "Withdrawal", "All").contains(transactionType)) {

            value = true;
        }

        return value;
    }


    public boolean activeAccountValidator(String userEmail) {

        boolean value = false;

        if(accountRepository.findAccountByUserEmail(userEmail).getAccountStatus().getAccountStatus()
                .equals("Active")) {

            value = true;
        }

        return value;
    }


    public List<Transaction> filterTransactionsByTransactionType(String transactionType) {

        List<Transaction> newList = new ArrayList<>();

        if(transactionTypeValidator(transactionType)) {

            if(!transactionType.equals("All")) {

                newList = transactionRepository.findAll().stream().filter(t -> t.getTransactionType()
                        .getTransactionType().equals(transactionType)).collect(Collectors.toList());

            }else{

                newList = transactionRepository.findAll();
            }
        }

        return newList;
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
