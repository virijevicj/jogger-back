package com.jogger.rs.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repozitorijum zaduzen za rad sa korisnicima.
 *
 * @author Jovan Virijevic
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    /*
     ActiveTrue ide uvek zato sto radimo soft delete
     */

    /**
     * Metoda koja pronalazi aktivnog korisnika na osnovu username-a.
     *
     * @param username korisnicko ime
     * @return Optional<User>
     */
    Optional<User> findByUsernameAndActiveTrue(String username);

    /**
     * Metoda koja pronalazi aktivnog korisnika na osnovu kljuca.
     *
     * @param id jedinstveni identifikator korisnika
     * @return Optional<User>
     */
    Optional<User> findByKeyUserAndActiveTrue(Integer id);
}
