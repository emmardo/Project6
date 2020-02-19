package com.openclassrooms.Project6.Repositories;

import com.openclassrooms.Project6.Models.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionTypeRepository extends JpaRepository<TransactionType, Integer> {

    TransactionType findTransactionTypeById(int id);

    TransactionType findTransactionTypeByTransactionType(String transactionType);
}
