package com.jogger.rs.utils;

import com.jogger.rs.dto.NewCommentDto;
import com.jogger.rs.dto.NewLMDto;
import com.jogger.rs.dto.UserDto;
import com.jogger.rs.labels.ErrorMessage;
import com.jogger.rs.user.User;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.regex.Pattern;

/**
 * Servis zaduzen za izvrsavanje validacije.
 *
 * @author Jovan Virijevic
 */
@Component
@CommonsLog
public final class Validator {

    /**
     * Metoda koja validira username korisnika.
     *
     * @param username korisnicko ime
     * @return
     * <ul>
     *     <li> true - ako je username validan </li>
     *     <li> true - ako username nije validan </li>
     * </ul>
     */
    public boolean validateUsername(String username) {
        if (!StringUtils.hasText(username) || username.length() < 6) return false;
        return Pattern.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)[A-Za-z\\d?\\!\\-\\_\\.]+$", username);
    }

    /**
     * Metoda koja validira lozinku korisnika.
     *
     * @param password lozinka
     * @return
     * <ul>
     *     <li> true - ako je lozinka validna </li>
     *     <li> true - ako lozinka nije validna </li>
     * </ul>
     */
    public boolean validatePassword(String password) {
        if (!StringUtils.hasText(password) || (password.length() < 10 && password.length() > 20)) return false;
        return Pattern.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[.,!?-_])[A-Za-z\\d.!?-_]+$", password);
    }

    /**
     * Metoda koja validira korisnika koji se dodaje u sistem.
     *
     * @param user novi korisnik
     * @return
     * <ul>
     *     <li> prazan string - ako je novi korisnik prosao validaciju, odnosno ne postoji poruka greske </li>
     *     <li> poruku greske - ako korisnik nije prosao validaciju </li>
     * </ul>
     */
    public String validateNewUser(UserDto user) {
        StringBuilder errorMessage = new StringBuilder();
        if (!validateUsername(user.getUsername())) errorMessage.append(ErrorMessage.USERNAME_VALIDATION);
        if (!validatePassword(user.getPassword())) errorMessage.append(ErrorMessage.PASSWORD_VALIDATION);
        if (!StringUtils.hasText(user.getFirstName())) errorMessage.append(ErrorMessage.FIRST_NAME_VALIDATION);
        if (!StringUtils.hasText(user.getLastName())) errorMessage.append(ErrorMessage.LAST_NAME_VALIDATION);
        if (!validateEmail(user.getEmail())) errorMessage.append(ErrorMessage.EMAIL_VALIDATION);
        if (ObjectUtils.isEmpty(user.getRoles())) errorMessage.append(ErrorMessage.ROLE_VALIDATION);
        return errorMessage.isEmpty() ? "" : errorMessage.toString();
    }

    /**
     * Metoda koja validira email korisnika.
     *
     * @param email lozinka
     * @return
     * <ul>
     *     <li> true - ako je email validan </li>
     *     <li> true - ako email nije validan </li>
     * </ul>
     */
    public boolean validateEmail(String email) {
        return StringUtils.hasText(email) && Pattern.matches("^[a-zA-Z0-9._-]+@gmail\\.com$", email);
    }

    /**
     * Metoda koja validira postojeceg korisnika cije se vrednosti menjaju.
     *
     * @param user izmenjene vrednosti korisnika
     * @return
     * <ul>
     *     <li> prazan string - ako su izmenjene vrednosti prosle validaciju, odnosno ne postoji poruka greske </li>
     *     <li> poruku greske - ako izmenjen korisnik nije prosao validaciju </li>
     * </ul>
     */
    public String validateEditedUser(UserDto user) {
        StringBuilder errorMessage = new StringBuilder();
        if (StringUtils.hasText(user.getUsername()) && !validateUsername(user.getUsername())) errorMessage.append(ErrorMessage.USERNAME_VALIDATION);
        if (StringUtils.hasText(user.getPassword()) && !validatePassword(user.getPassword())) errorMessage.append(ErrorMessage.PASSWORD_VALIDATION);
        if (user.getEmail() != null && !validateEmail(user.getEmail())) errorMessage.append(ErrorMessage.EMAIL_VALIDATION);
        return errorMessage.isEmpty() ? "" : errorMessage.toString();
    }

    /**
     * Metoda koja validira novi komentar.
     *
     * @param commentDto novi komentar
     * @return
     * <ul>
     *     <li> prazan string - ako je novi komentar prosao validaciju, odnosno ne postoji poruka greske </li>
     *     <li> poruku greske - ako novi komentar nije prosao validaciju </li>
     * </ul>
     */
    public String validateNewComment(NewCommentDto commentDto) {
        StringBuilder errorMessage = new StringBuilder();
        if (!StringUtils.hasText(commentDto.getText())) errorMessage.append(ErrorMessage.NO_TEXT_PRESENT);
        if (commentDto.getGrade() == null) errorMessage.append(ErrorMessage.NO_GRADE_PRESENT);
        else if (commentDto.getGrade() < 1 || commentDto.getGrade() > 10) errorMessage.append(ErrorMessage.GRADE_VALUE_VALIDATION);
        return errorMessage.isEmpty() ? "" : errorMessage.toString();
    }

    /**
     * Metoda koja validira novi materijal za ucenje.
     *
     * @param newLMDto novi materijal za ucenje
     * @return
     * <ul>
     *     <li> prazan string - ako je novi materijal za ucenje prosao validaciju, odnosno ne postoji poruka greske </li>
     *     <li> poruku greske - ako novi materijal za ucenje nije prosao validaciju </li>
     * </ul>
     */
    public String validateNewLM(NewLMDto newLMDto) {
        StringBuilder errorMessage = new StringBuilder();
        if (!StringUtils.hasText(newLMDto.getDescription())) errorMessage.append(ErrorMessage.NO_LM_DESCRIPTION);
        if (!StringUtils.hasText(newLMDto.getLink())) errorMessage.append(ErrorMessage.NO_LM_LINK);
        if (newLMDto.getArea() == null) errorMessage.append(ErrorMessage.NO_LM_AREA);
        if (newLMDto.getLevel() == null) errorMessage.append(ErrorMessage.NO_LM_LEVEL);
        if (newLMDto.getPlatform() == null) errorMessage.append(ErrorMessage.NO_LM_PLATFORM);
        if (newLMDto.getTechnology() == null) errorMessage.append(ErrorMessage.NO_LM_TECHNOLOGY);
        if (newLMDto.getContentType() == null) errorMessage.append(ErrorMessage.NO_LM_CONTENT_TYPE);
        return errorMessage.isEmpty() ? "" : errorMessage.toString();
    }
}
