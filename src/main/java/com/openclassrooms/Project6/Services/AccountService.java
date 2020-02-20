package com.openclassrooms.Project6.Services;

import com.openclassrooms.Project6.Models.Account;
import com.openclassrooms.Project6.Models.AccountStatus;
import com.openclassrooms.Project6.Repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {


    @Autowired
    private AccountRepository accountRepository;


    public Account getAccountByUserEmail(String email) {

        return accountRepository.findAccountByUserEmail(email);
    }


    public List<Account> getAllAccountsByType(String accountType) {

        return accountRepository.findAll().stream()
                .filter(a -> a.getAccountType().getAccountType().equals(accountType))
                .collect(Collectors.toList());
    }


    public void updateAccountStatus(String email, String newStatus) {

        Account account = accountRepository.findAccountByUserEmail(email);

        AccountStatus newAccountStatus = new AccountStatus(newStatus);

        account.setAccountStatus(newAccountStatus);

        accountRepository.save(account);
    }
}
