package com.openclassrooms.Project6.Repositories;

import com.openclassrooms.Project6.Models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    List<Transaction> findTransactionsByAccountUserEmail(String userEmail);

    List<Transaction> findTransactionsByConnectionUserEmail(String userEmail);
}
