package com.assesment.Multisig.repository;

import com.assesment.Multisig.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Map;

@RepositoryRestResource
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "select user_id,username from users ", nativeQuery = true)
    List<Map<Long,String>> fetchUsers();

    @Query(value = "select * from users u where u.user_id = :userId", nativeQuery = true)
    User fetchUserById(@Param("userId") Long userId);

    @Query(value = "select mail from users u where u.user_id = :userId", nativeQuery = true)
    String fetchUsermailById(@Param("userId") Long userId);
}
