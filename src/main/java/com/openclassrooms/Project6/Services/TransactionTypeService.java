package com.openclassrooms.Project6.Services;

import com.openclassrooms.Project6.Models.TransactionType;
import com.openclassrooms.Project6.Repositories.TransactionTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionTypeService {

    @Autowired
    private TransactionTypeRepository transactionTypeRepository;


    public void createTransactionTypes(List<String> transactionTypeList) {

        transactionTypeRepository.deleteAll();

        for (String transactionType: transactionTypeList) {

            TransactionType transactionTypeInstance = new TransactionType(transactionType);

            transactionTypeRepository.save(transactionTypeInstance);
        }
    }

    public void addTransactionType(String transactionType) {

        TransactionType transactionTypeInstance = new TransactionType(transactionType);

        transactionTypeRepository.save(transactionTypeInstance);
    }

    public List<String> getAllTransactionTypes() {

        List<String> transactionTypes = null;

        if(!transactionTypeRepository.findAll().isEmpty()) {

            for (TransactionType transactionType : transactionTypeRepository.findAll()) {

                transactionTypes.add(transactionType.getTransactionType());
            }
        }

        return transactionTypes;
    }

    public String getTransactionTypeById(int transactionTypeId) {

        return transactionTypeRepository.findTransactionTypeById(transactionTypeId).getTransactionType();
    }

    public void deleteTransactionType(String transactionType) {

        transactionTypeRepository.delete(transactionTypeRepository.findTransactionTypeByTransactionType(transactionType));
    }
}
