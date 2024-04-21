package com.jogger.rs.user;

import com.jogger.rs.dto.LoginRequestDto;
import com.jogger.rs.dto.LoginResponseDto;
import com.jogger.rs.dto.UserDto;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Servis zaduzen za rad sa korisnicima.
 *
 * @author Jovan Virijevic
 */
public interface UserServiceInterface {

    /**
     * Metoda koja prijavljuje korisnika na sistem.
     *
     * @param loginRequestDto username i lozinka za prijavljivanje
     * @return LoginResponseDto
     * @throws IllegalArgumentException ako parametri logovanja nisu validni
     * @throws NoSuchElementException ako nije pronadjen korisnik sa datim parametrima logovanja
     */
    Optional<LoginResponseDto> login(LoginRequestDto loginRequestDto) throws IllegalArgumentException, NoSuchElementException;

    /**
     * Metoda koja pronalazi sve korisnike u sistemu (aktivne + obrisane)
     *
     * @return listu korisnika sistema
     */
    List<User> findAll();

    /**
     * Metoda koja pronalazi aktivnog korisnika na osnovu kljuca.
     *
     * @param id kljuc korisnika
     * @return korisnik sistema
     */
    Optional<User> findById(Integer id);

    /**
     * Metoda koja brise korisnika na osnovu kljuca.
     *
     * @param id kljuc korisnika
     * @throws NoSuchElementException ako ne postoji korisnik u sistemu sa prosledjenim kljucem
     */
    void deleteById(Integer id) throws NoSuchElementException;

    /**
     * Metoda koja dodaje novog korisnika u sistem.
     *
     * @param newUser nov korisnik
     * @throws IllegalArgumentException ako nov korisnik nema sve validne vrednosti
     */
    void save(UserDto newUser) throws IllegalArgumentException;

    /**
     * Metoda koja azurira korisnika.
     *
     * @param user azurirani korisnik
     * @return
     * <ul>
     *     <li> true - ako je korisnik azuriran </li>
     *     <li> false - ako korisnik nije azuriran </li>
     * </ul>
     */
    boolean update(UserDto user);
}
