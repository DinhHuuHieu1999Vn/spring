package com.vn.spring.repository;

import com.vn.spring.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
//@RepositoryRestResource
public interface UsersRepository extends JpaRepository<Users, Long> {
    @Query("SELECT u FROM Users u WHERE u.email = ?1")
    Optional<Users> findUsersByEmail(String email);

    @Query("SELECT u FROM Users u WHERE u.username = ?1")
    Optional<Users> findUsersByUsername(String username);
}
