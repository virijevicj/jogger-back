package com.jogger.rs.labels;

public interface ErrorMessage {

    public final static String SOMETHING_WENT_WRONG = "Servis nije uspeo da izvrsi trazenu operaciju";
    public final static String ACCESS_FORBIDDEN = "Nemate prava da pristupe resursu na putanji: ";
    public final static String EMPTY_REQUEST_BODY = "Prosledjen je prazan objekat kao request body, a ocekivan je: ";
    public final static String USERNAME_VALIDATION = "Username mora sadrzati najmanje 6 karaktera, 1 malo, jedno veliko slovo, 1 broj i opciono neki od sledecih 5 karaktera: ., !, ?, -, _.";
    public final static String PASSWORD_VALIDATION = "Sifra mora sadrzati najmanje 10 karaktera, 1 malo, jedno veliko slovo, 1 broj i neki od sledecih 5 karaktera: ., !, ?, -, _.";
    public final static String WRONG_PASSWORD = "Uneta pogresna sifra za username: ";
    public final static String NO_USER_FOUND_WITH_USERNAME = "Ne postoji korisnik: ";
    public final static String NO_TOKEN_FOUND = "Pogresan token: ";
    public final static String NO_RULES_ADDED = "Potrebno je dodati uloge koje smeju da koriste resurs %s za metodu %s na putanji %s";
    public final static String NO_ID_PASSED = "Nije prosledjen odgovarajuci id";
    public final static String NO_USER_FOUND_WITH_ID = "Nije pronadjen nijedan user za id: ";
    public final static String FIRST_NAME_VALIDATION = "Ime je obavezno polje za unos.";
    public final static String LAST_NAME_VALIDATION = "Prezime je obavezno polje za unos.";
    public final static String USERNAME_ALREADY_EXISTS = "Vec postoji korisnik za username: ";
    public final static String EMAIL_VALIDATION = "Email mora biti u obliku nekiTekst@gmail.com gde nekiTekst sme sadrzati mala i velika slova, brojeve i specijalne karaktere: ., -, _.";
    public final static String NO_TOOKEN_IN_HEADER = "Header nije lepo konfigurisan - Token nije prosledjen!";
}
