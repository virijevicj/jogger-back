package com.jogger.rs.labels;

public interface SuccessMessage {
    public final static String LOGIN_SUCCESS = "Uspesan login";
    public final static String LOGOUT_SUCCESS = "Uspesan logout";
    public final static String USER_SAVE_SUCCESS = "Uspesno kreiran nov korisnik: ";
    public final static String USER_UPDATE_SUCCESS = "Uspesno azuriran korisnik za id: ";
    public final static String USER_UPDATE_SUCCESS_BUT_NOTHING_WAs_DIFFERENT = "Prilikom azuriranja, nijedna vresnost prosledjena se ne razlikuje za korisnika: ";
    public final static String USER_DELETE_SUCCESS = "Uspesno obrisan korisnik za id: ";
    public final static String COMMENT_SAVE_SUCCESS = "Uspesno dodata nova rezencija";
    public final static String LM_SAVE_SUCCESS = "Uspesno dodat novi materijal za ucenje";
}
