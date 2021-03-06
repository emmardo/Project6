package com.openclassrooms.Project6.Repositories;

import com.openclassrooms.Project6.Models.AccountStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountStatusRepository extends JpaRepository<AccountStatus, Integer> {

    AccountStatus findAccountStatusById(int id);

    AccountStatus findAccountStatusByAccountStatus(String accountStatus);
}
