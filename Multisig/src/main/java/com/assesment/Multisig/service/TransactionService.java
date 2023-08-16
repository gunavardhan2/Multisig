package com.assesment.Multisig.service;

import com.assesment.Multisig.model.Transaction;
import com.assesment.Multisig.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;
    public Long createTransaction(Long creatorId) {
        Transaction transaction = new Transaction();
        transaction.setCreatorId(creatorId);
        Transaction transaction1 = transactionRepository.save(transaction);
        return transaction1.getTransactionId();
    }

    public void updateTransaction(Long transactionId) {
        Transaction transaction = new Transaction();
        transaction = transactionRepository.getTransactionDetails(transactionId);
        transaction.setApproved(true);
        transactionRepository.save(transaction);
    }
}
