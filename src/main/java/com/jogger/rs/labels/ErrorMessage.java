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
    public final static String NO_TEXT_PRESENT = "Komentar je obavezna stavka prilikom recenzije materijala za ucenje.";
    public final static String NO_GRADE_PRESENT = "Ocena je obavezna stavka prilikom recenzije materijala za ucenje.";
    public final static String NO_LEARNING_MATERIAL_FOUND = "Nije pronadjen materijal sa id-jem: ";
    public final static String GRADE_VALUE_VALIDATION = "Ocena mora da ima vrednost izmedju 1 i 10.";
    public final static String NO_LM_DESCRIPTION = "Opis je obavezno polje. ";
    public final static String NO_LM_LINK = "Link je obavezno polje. ";
    public final static String NO_LM_AREA = "Oblast je obavezno polje. ";
    public final static String NO_LM_LEVEL = "Nivo je obavezno polje. ";
    public final static String NO_LM_PLATFORM = "Platforma je obavezno polje. ";
    public final static String NO_LM_TECHNOLOGY = "Tehnologija je obavezno polje. ";
    public final static String NO_LM_CONTENT_TYPE = "Tip sadrzaja je obavezno polje. ";
    public final static String NO_AREA_WITH_NAME = "Ne postoji oblast sa imenom: ";
    public final static String NO_PLATFORM_WITH_NAME = "Ne postoji platforma sa imenom: ";
    public final static String NO_TECHNOLOGY_WITH_NAME = "Ne postoji tehnologija sa imenom: ";
    public final static String NO_LEVEL_WITH_NAME = "Ne postoji level sa imenom: ";
    public final static String NO_CONTENT_TYPE_WITH_NAME = "Ne postoji tip sadrzaja sa imenom: ";
    public final static String ROLE_VALIDATION = "Korisnik mora imati barem 1 ulogu u sistemu. ";
}
