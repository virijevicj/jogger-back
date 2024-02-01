package com.jogger.rs.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    /*
     ActiveTrue ide uvek zato sto radimo soft delete
     */
    Optional<User> findByUsernameAndActiveTrue(String username);
}
