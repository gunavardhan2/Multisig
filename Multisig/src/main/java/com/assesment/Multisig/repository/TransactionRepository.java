package com.assesment.Multisig.repository;

import com.assesment.Multisig.model.SignRequest;
import com.assesment.Multisig.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query(value = "select * from transaction t where t.transaction_id = :transactionId" , nativeQuery = true)
    Transaction getTransactionDetails(Long transactionId);
}
