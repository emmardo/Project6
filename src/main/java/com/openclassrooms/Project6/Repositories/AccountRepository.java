package com.openclassrooms.Project6.Repositories;

import com.openclassrooms.Project6.Models.Account;
import com.openclassrooms.Project6.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    Account findAccountByUserEmail(String email);
}
