package com.openclassrooms.Project6.Repositories;

import com.openclassrooms.Project6.Models.Iban;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IbanRepository extends JpaRepository<Iban, Integer> {

    /*Iban findById(int ibanId);*/

    List<Iban> findByAccount_UserEmail(String email);

    /*void deleteIbanByIban(String iban);

    void deleteAllByAccountUserEmail(String email);*/
}
