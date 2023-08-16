package com.assesment.Multisig.repository;

import com.assesment.Multisig.model.SignRequest;
import com.assesment.Multisig.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface SignRequestRepository extends JpaRepository<SignRequest, Long> {
    @Query(value = "select * from sign_request s where s.user_id = :userId and s.transaction_id = :transactionId" , nativeQuery = true)
    SignRequest fetchSignDetails(Long userId, Long transactionId);

    @Query(value = "select * from sign_request s where s.transaction_id = :transactionId" , nativeQuery = true)
    List<SignRequest> fetchAllSignRequestById(Long transactionId);
}
