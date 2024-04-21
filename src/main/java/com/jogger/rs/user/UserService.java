package com.jogger.rs.user;

import com.jogger.rs.dto.LoginRequestDto;
import com.jogger.rs.dto.LoginResponseDto;
import com.jogger.rs.dto.UserDto;
import com.jogger.rs.email.service.EmailServiceInterface;
import com.jogger.rs.jwt.JWTService;
import com.jogger.rs.labels.ErrorMessage;
import com.jogger.rs.role.Role;
import com.jogger.rs.role.RoleName;
import com.jogger.rs.role.RoleServiceInterface;
import com.jogger.rs.utils.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.apache.commons.collections4.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * Implementacija servisa zaduzenog za rad sa korisnicima.
 *
 * @author Jovan Virijevic
 */
@CommonsLog
@Service
@RequiredArgsConstructor
public class UserService implements UserServiceInterface{

    /**
     * Servis zaduzen za rad sa korsinicima.
     */
    private final UserRepository userRepository;

    /**
     * Servis zaduzen za validaciju.
     */
    private final Validator validator;

    /**
     * Klasa zaduzena za enkriptovanje lozinke korisnika.
     */
    private final PasswordEncoder bcrypt;

    /**
     * Klasa zaduzena za mapiranje objekata.
     */
    private final ModelMapper modelMapper;

    /**
     * Servis zaduzen za rad sa ulogama.
     */
    private final RoleServiceInterface roleService;

    /**
     * Servis zaduzen za slanje mejlova.
     */
    private final EmailServiceInterface emailService;

    /**
     * Servis zaduzen za rad sa jwt tokenima.
     */
    private final JWTService jwtService;

    @Override
    public Optional<LoginResponseDto> login(LoginRequestDto loginRequestDto) throws IllegalArgumentException, NoSuchElementException {
        if (ObjectUtils.isEmpty(loginRequestDto))
                throw new IllegalArgumentException(ErrorMessage.EMPTY_REQUEST_BODY + loginRequestDto.getClass().getName());

        String username = loginRequestDto.getUsername();
        if (!validator.validateUsername(username))
            throw new IllegalArgumentException(ErrorMessage.USERNAME_VALIDATION);

        String password = loginRequestDto.getPassword();
        if (!validator.validatePassword(password))
            throw new IllegalArgumentException(ErrorMessage.PASSWORD_VALIDATION);

        Optional<User> optional = userRepository.findByUsernameAndActiveTrue(username);
        User user = optional.orElseThrow(() -> new NoSuchElementException(ErrorMessage.NO_USER_FOUND_WITH_USERNAME + username));

        if (!bcrypt.matches(password, user.getPassword()))
            throw new NoSuchElementException(ErrorMessage.WRONG_PASSWORD + username);

        String token = jwtService.generateToken(username);
        List<String> roles = user.getRoles()
                .stream()
                .map(role -> role.getName().toString())
                .toList();
        LoginResponseDto responseDto = LoginResponseDto.builder()
                .token(token)
                .roles(roles)
                .build();

        return Optional.ofNullable(responseDto);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(Integer id) throws IllegalArgumentException {
        return userRepository.findByKeyUserAndActiveTrue(id);
    }

    @Override
    public void deleteById(Integer id) throws NoSuchElementException {
        User user = findById(id).orElseThrow(() ->
                new NoSuchElementException(ErrorMessage.NO_USER_FOUND_WITH_ID + id));
        user.setActive(false);
        userRepository.save(user);
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
        emailService.sendEmailWithUsernameAndPassword(user.getEmail(), user.getUsername(), newUser.getPassword());
    }

    @Override
    public boolean update(UserDto userDto) throws IllegalArgumentException, NoSuchElementException {
        Integer id = userDto.getKeyUser();
        User user = findById(id).orElseThrow(() ->
                new NoSuchElementException(ErrorMessage.NO_USER_FOUND_WITH_ID + id));

        String errorMessage = validator.validateEditedUser(userDto);
        if (StringUtils.hasText(errorMessage))
            throw new IllegalArgumentException(errorMessage);

        boolean userUpdated = updateUserIfNeeded(user, userDto);
        boolean userRolesUpdated = updateUserRolesIfNeeded(user, userDto);
        if (userUpdated || userRolesUpdated) {
            userRepository.save(user);
        }

        return userUpdated || userRolesUpdated;
    }

    /**
     * Metoda koja pronalazi uloge za novog korisnika.
     *
     * @param newUser nov korisnik
     * @return listu uloga u sistemu
     */
    private List<Role> findUserRoles(UserDto newUser) {
        List<String> roleNames = newUser.getRoles();
        if (!ObjectUtils.isEmpty(roleNames))
            return roleService.findRolesByNames(roleNames);
        return Collections.emptyList();
    }

    /**
     * Metoda koja azurira korisnicke uloge ako je to potrebno.
     *
     * @param user trenutni korisnik
     * @param userDto azurirani korisnik
     * @return
     * <ul>
     *     <li> true - ako su uloge azurirane </li>
     *     <li> false - ako uloge nisu azurirane </li>
     * </ul>
     */
    private boolean updateUserRolesIfNeeded(User user, UserDto userDto) {
        List<String> newRoles = userDto.getRoles();
        if (newRoles == null) return false; // nije moguce da user nema nijednu ulogu u sistemu!!!

        List<RoleName> oldRoles = user.getRoles()
                .stream()
                .map(Role::getName)
                .toList();

        if (!CollectionUtils.isEqualCollection(newRoles, oldRoles)) {
            List<Role> roles = findUserRoles(userDto);
            user.setRoles(roles);
            return true;
        }
        return false;
    }

    /**
     * Metoda koja menja vrednosti korisnika sa novim vrednostima.
     *
     * @param user trenutni korisnik
     * @param userDto azurirani korisnik
     * @return
     * <ul>
     *     <li> true - ako je korisnik azuriran </li>
     *     <li> false - ako korisnik nije azuriran </li>
     * </ul>
     */
    private boolean updateUserIfNeeded(User user, UserDto userDto) {
        // fizikalisanje :(
        boolean isUpdated = false;
        if (userDto.getUsername() != null && !userDto.getUsername().equals(user.getUsername())) {
            isUpdated = true;
            user.setUsername(userDto.getUsername());
        }
        if (userDto.getPassword() != null && !bcrypt.matches(userDto.getPassword(), user.getPassword())) {
            isUpdated = true;
            user.setPassword(bcrypt.encode(userDto.getPassword()));
        }
        if (userDto.getFirstName() != null && !userDto.getFirstName().equals(user.getFirstName())) {
            isUpdated = true;
            user.setFirstName(userDto.getFirstName());
        }
        if (userDto.getLastName() != null && !userDto.getLastName().equals(user.getLastName())) {
            isUpdated = true;
            user.setLastName(userDto.getLastName());
        }
        if (userDto.getEmail() != null && !userDto.getEmail().equals(user.getEmail())) {
            isUpdated = true;
            user.setEmail(userDto.getEmail());
        }
        return isUpdated;
    }
}
