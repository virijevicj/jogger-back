package com.jogger.rs.user;

import com.jogger.rs.auth.SessionManager;
import com.jogger.rs.auth.TokenFactory;
import com.jogger.rs.dto.LoginRequestDto;
import com.jogger.rs.dto.LoginResponseDto;
import com.jogger.rs.dto.UserDto;
import com.jogger.rs.labels.ErrorMessage;
import com.jogger.rs.role.Role;
import com.jogger.rs.role.RoleServiceInterface;
import com.jogger.rs.utils.Validator;
import lombok.extern.apachecommons.CommonsLog;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.*;

@CommonsLog
@Service
public class UserService implements UserServiceInterface{

    private UserRepository userRepository;
    private Validator validator;
    private PasswordEncoder bcrypt;
    private TokenFactory tokenFactory;
    private SessionManager sessionManager;
    private ModelMapper modelMapper;
    private RoleServiceInterface roleService;

    @Autowired
    public UserService(UserRepository userRepository, Validator validator, PasswordEncoder bcrypt, SessionManager sessionManager,
                       TokenFactory tokenFactory, ModelMapper modelMapper, RoleServiceInterface roleService) {
        this.userRepository = userRepository;
        this.validator = validator;
        this.bcrypt = bcrypt;
        this.sessionManager = sessionManager;
        this.tokenFactory = tokenFactory;
        this.modelMapper = modelMapper;
        this.roleService = roleService;
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

    public Optional<User> findById(Integer id) throws IllegalArgumentException {
        return userRepository.findByKeyUserAndActiveTrue(id);
    }

    @Override
    public void deleteById(Integer id, String token) throws NoSuchElementException {
        User user = findById(id).orElseThrow(() ->
                new NoSuchElementException(ErrorMessage.NO_USER_FOUND_WITH_ID + id));
        user.setActive(false);
        userRepository.save(user);
        sessionManager.deleteUserSession(token);
    }

    @Override
    public void save(UserDto newUser) throws IllegalArgumentException {
        String errorMessage = validator.validateNewUser(newUser);
        if (StringUtils.hasText(errorMessage))
            throw new IllegalArgumentException(errorMessage);
        if (userRepository.findByUsernameAndActiveTrue(newUser.getUsername()).isPresent())
            throw new IllegalArgumentException(ErrorMessage.USERNAME_ALREADY_EXISTS + newUser.getUsername());
        User user = modelMapper.map(newUser, User.class);
        user.setPassword(bcrypt.encode(user.getPassword()));
        if (ObjectUtils.isEmpty(user.getActive()))
            user.setActive(true);
        user.setRoles(findUserRoles(newUser));
        userRepository.save(user);
    }
    private List<Role> findUserRoles(UserDto newUser) {
        List<String> roleNames = newUser.getRoles();
        if (!ObjectUtils.isEmpty(roleNames))
            return roleService.findRolesByNames(roleNames);
        return Collections.emptyList();
    }
}
