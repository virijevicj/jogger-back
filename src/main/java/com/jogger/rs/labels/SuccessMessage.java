package com.jogger.rs.labels;

/**
 * Interfejs u kojem se nalaze poruke kada je operacija uspesno izvrsena.
 *
 * @author Jovan Virijevic
 */
public interface SuccessMessage {
    /**
     * Poruka koja se salje kada je login uspesno izvrsen.
     */
    public final static String LOGIN_SUCCESS = "Uspesan login";
    /**
     * Poruka koja se salje kada je logout uspesno izvrsen.
     */
    public final static String LOGOUT_SUCCESS = "Uspesan logout";
    /**
     * Poruka koja se salje kada je uspesno kreiran novi korisnik.
     */
    public final static String USER_SAVE_SUCCESS = "Uspesno kreiran nov korisnik: ";
    /**
     * Poruka koja se salje kada je uspesno azuriran korisnik.
     */
    public final static String USER_UPDATE_SUCCESS = "Uspesno azuriran korisnik za id: ";
    /**
     * Poruka koja se salje kada se prilikom azuriranja nijedna vrednost ne razlikuje za odredjenog korisnika.
     */
    public final static String USER_UPDATE_SUCCESS_BUT_NOTHING_WAs_DIFFERENT = "Prilikom azuriranja, nijedna vresnost prosledjena se ne razlikuje za korisnika: ";
    /**
     * Poruka koja se salje kada je uspesno obrisan korisnik za odredjeni id.
     */
    public final static String USER_DELETE_SUCCESS = "Uspesno obrisan korisnik za id: ";
    /**
     * Poruka koja se salje kada je uspesno dodata nova recenzija.
     */
    public final static String COMMENT_SAVE_SUCCESS = "Uspesno dodata nova rezencija";
    /**
     * Poruka koja se salje kada je uspesno dodat novi materijal za ucenje.
     */
    public final static String LM_SAVE_SUCCESS = "Uspesno dodat novi materijal za ucenje";
    /**
     * Poruka koja se salje kada je uspesno obrisan materijal za ucenje.
     */
    public final static String LM_DELETE_SUCCESS = "Uspesno obrisan materijal za ucenje";

}
