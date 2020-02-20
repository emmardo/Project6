package com.openclassrooms.Project6.Services;

import com.openclassrooms.Project6.Models.Account;
import com.openclassrooms.Project6.Models.Iban;
import com.openclassrooms.Project6.Repositories.AccountRepository;
import com.openclassrooms.Project6.Repositories.IbanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IbanService {

    @Autowired
    private IbanRepository ibanRepository;

    @Autowired
    AccountRepository accountRepository;


    public void createIban(String email, String iban) {

        Account account = accountRepository.findAccountByUserEmail(email);

        Iban newIban = new Iban(account, iban);

        ibanRepository.save(newIban);
    }


    public Iban getIbanByIbanId(int ibanId) {

        return ibanRepository.findById(ibanId);
    }


    public List<Iban> getAllIbans() {

        return ibanRepository.findAll();
    }


    public List<Iban> getAllIbansByAccount(Account account) {

        return ibanRepository.findIbanByAccount(account);
    }


    public List<Iban> getAllIbansByEmail(String email) {

        return ibanRepository.findByAccount_UserEmail(email);
    }


    public void updateIban(String existingIban, String newIban) {

        Iban iban = getAllIbans().stream().filter(i -> i.getIban().equals(existingIban)).findFirst().get();

        iban.setIban(newIban);

        ibanRepository.save(iban);
    }


    public void deleteIbanById(int ibanId) {

        ibanRepository.deleteById(ibanId);
    }


    public void deleteIbanByIban(String iban) {

        ibanRepository.deleteIbanByIban(iban);
    }


    public void deleteAllIbanByEmail(String email) {

        ibanRepository.deleteAllByAccountUserEmail(email);
    }
}
