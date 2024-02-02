package com.jogger.rs.utils;

import com.jogger.rs.dto.UserDto;
import com.jogger.rs.labels.ErrorMessage;
import com.jogger.rs.user.User;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.regex.Pattern;

@Component
public final class Validator {

    // username validacija: najmanje 6 slova, najmanje 1 veliko slovo, najmanje 1 broj
    public boolean validateUsername(String username) {
        if (!StringUtils.hasText(username) || username.length() < 6) return false;
        return Pattern.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)[A-Za-z\\d?\\!\\-\\_\\.]+$", username);
    }

    // ISTO ZA SIFRU: 10 karaktera, najvise 20 i mora da ima neki obavezni specijalni karakter
    public boolean validatePassword(String password) {
        if (!StringUtils.hasText(password) || (password.length() < 10 && password.length() > 20)) return false;
        return Pattern.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[.,!?-_])[A-Za-z\\d.!?-_]+$", password);
    }

    public String validateNewUser(UserDto user) {
        StringBuilder errorMessage = new StringBuilder();
        if (!validateUsername(user.getUsername())) errorMessage.append(ErrorMessage.USERNAME_VALIDATION);
        if (!validatePassword(user.getPassword())) errorMessage.append(ErrorMessage.PASSWORD_VALIDATION);
        if (!StringUtils.hasText(user.getFirstName())) errorMessage.append(ErrorMessage.FIRST_NAME_VALIDATION);
        if (!StringUtils.hasText(user.getLastName())) errorMessage.append(ErrorMessage.LAST_NAME_VALIDATION);
        if (!validateEmail(user.getEmail())) errorMessage.append(ErrorMessage.EMAIL_VALIDATION);
        return errorMessage.isEmpty() ? "" : errorMessage.toString();
    }

    public boolean validateEmail(String email) {
        return StringUtils.hasText(email) && Pattern.matches("^[a-zA-Z0-9._-]+@gmail\\.com$", email);
    }

    public String validateEditedUser(UserDto user) {
        StringBuilder errorMessage = new StringBuilder();
        if (StringUtils.hasText(user.getUsername()) && !validateUsername(user.getUsername())) errorMessage.append(ErrorMessage.USERNAME_VALIDATION);
        if (StringUtils.hasText(user.getPassword()) && !validatePassword(user.getPassword())) errorMessage.append(ErrorMessage.PASSWORD_VALIDATION);
        if (user.getEmail() != null && !validateEmail(user.getEmail())) errorMessage.append(ErrorMessage.EMAIL_VALIDATION);
        return errorMessage.isEmpty() ? "" : errorMessage.toString();
    }
}
