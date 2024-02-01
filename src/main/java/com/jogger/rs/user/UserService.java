package com.jogger.rs.user;

import com.jogger.rs.auth.SessionManager;
import com.jogger.rs.auth.TokenFactory;
import com.jogger.rs.dto.LoginRequestDto;
import com.jogger.rs.dto.LoginResponseDto;
import com.jogger.rs.labels.ErrorMessage;
import com.jogger.rs.utils.Validator;
import lombok.extern.apachecommons.CommonsLog;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@CommonsLog
@Service
public class UserService implements UserServiceInterface{

    private UserRepository userRepository;
    private Validator validator;
    private PasswordEncoder bcrypt;
    private TokenFactory tokenFactory;
    private SessionManager sessionManager;
    private ModelMapper modelMapper;

    @Autowired
    public UserService(UserRepository userRepository, Validator validator, PasswordEncoder bcrypt, SessionManager sessionManager,
                       TokenFactory tokenFactory, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.validator = validator;
        this.bcrypt = bcrypt;
        this.sessionManager = sessionManager;
        this.tokenFactory = tokenFactory;
        this.modelMapper = modelMapper;
    }

    @Override
    public Optional<LoginResponseDto> login(LoginRequestDto loginRequestDto) throws IllegalArgumentException, NoSuchElementException {
        if (ObjectUtils.isEmpty(loginRequestDto))
                throw new IllegalArgumentException(ErrorMessage.EMPTY_REQUEST_BODY + loginRequestDto.getClass().getName());
        String username = loginRequestDto.getUsername();
        log.info("pokusava da se uloguje sa imenom " + username + " i sifrom " + loginRequestDto.getPassword());
        if (!validator.validateUsername(username))
            throw new IllegalArgumentException(ErrorMessage.USERNAME_VALIDATION);
        String password = loginRequestDto.getPassword();
        if (!validator.validatePassword(password))
            throw new IllegalArgumentException(ErrorMessage.PASSWORD_VALIDATION);
        Optional<User> optional = userRepository.findByUsernameAndActiveTrue(username);
        User user = optional.orElseThrow(() -> new NoSuchElementException(ErrorMessage.NO_USER_FOUND_WITH_USERNAME + username));
        if (!bcrypt.matches(password, user.getPassword()))
            throw new NoSuchElementException(ErrorMessage.WRONG_PASSWORD + username);
        String token = tokenFactory.generateToken();
        List<String> roles = user.getRoles()
                .stream()
                .map(role -> role.getName().toString())
                .toList();
        LoginResponseDto responseDto = LoginResponseDto.builder()
                .token(token)
                .roles(roles)
                .build();
        sessionManager.createUserSession(token, modelMapper.map(user, UserSession.class));
        return Optional.ofNullable(responseDto);
    }

    @Override
    public void logout(String token) {
        sessionManager.removeUserSession(token);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
