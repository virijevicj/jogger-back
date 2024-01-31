package com.jogger.rs.labels;

public interface ErrorMessage {

    public final static String SOMETHING_WENT_WRONG = "Servis nije uspeo da izvrsi trazenu operaciju";
    public final static String USERNAME_VALIDATION = "Username mora sadrzati najmanje 6 karaktera, 1 malo, jedno veliko slovo, 1 broj i opciono neki od sledecih 5 karaktera: ., !, ?, -, _.";
    public final static String PASSWORD_VALIDATION = "Sifra mora sadrzati najmanje 10 karaktera, 1 malo, jedno veliko slovo, 1 broj i neki od sledecih 5 karaktera: ., !, ?, -, _.";
    public final static String FIRST_NAME_VALIDATION = "Ime je obavezno polje za unos.";
    public final static String LAST_NAME_VALIDATION = "Prezime je obavezno polje za unos.";
    public final static String EMAIL_VALIDATION = "Email mora biti u obliku nekiTekst@gmail.com gde nekiTekst sme sadrzati mala i velika slova, brojeve i specijalne karaktere: ., -, _";
}
