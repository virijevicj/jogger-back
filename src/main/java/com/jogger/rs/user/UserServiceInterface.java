package com.jogger.rs.user;

import com.jogger.rs.dto.LoginRequestDto;
import com.jogger.rs.dto.LoginResponseDto;

import java.util.NoSuchElementException;
import java.util.Optional;

public interface UserServiceInterface {
    Optional<LoginResponseDto> login(LoginRequestDto loginRequestDto) throws IllegalArgumentException, NoSuchElementException;

}
