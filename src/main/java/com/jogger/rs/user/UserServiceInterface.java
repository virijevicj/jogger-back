package com.jogger.rs.user;

import com.jogger.rs.dto.LoginRequestDto;
import com.jogger.rs.dto.LoginResponseDto;
import com.jogger.rs.dto.UserDto;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public interface UserServiceInterface {
    Optional<LoginResponseDto> login(LoginRequestDto loginRequestDto) throws IllegalArgumentException, NoSuchElementException;

    void logout(String token);

    List<User> findAll();

    Optional<User> findById(Integer id);

    void deleteById(Integer id) throws NoSuchElementException;

    void save(UserDto newUser) throws IllegalArgumentException;

    boolean update(UserDto user, String token);
}
