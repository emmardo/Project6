package com.openclassrooms.Project6.Services;

import com.openclassrooms.Project6.Models.Account;
import com.openclassrooms.Project6.Models.AccountStatus;
import com.openclassrooms.Project6.Models.User;
import com.openclassrooms.Project6.Repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {


    @Autowired
    private AccountRepository accountRepository;


    public Account getAccountByUser(User user) {

        return accountRepository.findAccountByUser(user);
    }


    public Account getAccountByEmail(String email) {

        return accountRepository.findAccountByUserEmail(email);
    }


    public List<Account> getAllRegularAccounts() {

        return accountRepository.findAll().stream().filter(a -> a.getAccountType().getAccountType().equals("Regular")).collect(Collectors.toList());
    }


    public void updateAccountStatus(String email, String newStatus) {

        Account account = accountRepository.findAccountByUserEmail(email);

        AccountStatus newAccountStatus = new AccountStatus(newStatus);

        account.setAccountStatus(newAccountStatus);

        accountRepository.save(account);
    }


    public void updateAccountsCurrentBalance(String email, float balanceVariation) {

        if((accountRepository.findAccountByUserEmail(email).getCurrentBalance() + balanceVariation) > 0) {

            Account account = accountRepository.findAccountByUserEmail(email);

            float newCurrentBalance = (account.getCurrentBalance() + balanceVariation);

            account.setCurrentBalance(newCurrentBalance);

            accountRepository.save(account);
        }
    }


    public List<Account> getAllCompanyAccounts() {

        return accountRepository.findAll().stream().filter(a -> a.getAccountType().getAccountType().equals("Company")).collect(Collectors.toList());
    }
}
