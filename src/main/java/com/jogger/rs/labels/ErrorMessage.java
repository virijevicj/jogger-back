package com.jogger.rs.labels;

/**
 * Interfejs u kojem se nalaze poruke greske.
 *
 * @author Jovan Virijevic
 */
public interface ErrorMessage {
    /**
     * Poruka koja se salje kada servis nije uspeo da izvrsi transakciju.
     */
    public final static String SOMETHING_WENT_WRONG = "Servis nije uspeo da izvrsi trazenu operaciju";
    /**
     * Poruka koja se salje kada kosisnik nema prava da pristupi resursu na putanji.
     */
    public final static String ACCESS_FORBIDDEN = "Nemate prava da pristupe resursu na putanji: ";
    /**
     * Poruka koja se salje kada je prosledjen prazan objekat kao request body.
     */
    public final static String EMPTY_REQUEST_BODY = "Prosledjen je prazan objekat kao request body, a ocekivan je: ";
    /**
     * Poruka koja se salje kada nije prosla validacija username-a.
     */
    public final static String USERNAME_VALIDATION = "Username mora sadrzati najmanje 6 karaktera, 1 malo, jedno veliko slovo, 1 broj i opciono neki od sledecih 5 karaktera: ., !, ?, -, _.";
    /**
     * Poruka koja se salje kada nije prosla validacija sifre.
     */
    public final static String PASSWORD_VALIDATION = "Sifra mora sadrzati najmanje 10 karaktera, 1 malo, jedno veliko slovo, 1 broj i neki od sledecih 5 karaktera: ., !, ?, -, _.";
    /**
     * Poruka koja se salje kada je uneta pogresna sifra za neki username.
     */
    public final static String WRONG_PASSWORD = "Uneta pogresna sifra za username: ";
    /**
     * Poruka koja se salje kada ne postoji korisnik.
     */
    public final static String NO_USER_FOUND_WITH_USERNAME = "Ne postoji korisnik: ";
    /**
     * Poruka koja se salje kada je unet pogresan token.
     */
    public final static String NO_TOKEN_FOUND = "Pogresan token: ";
    /**
     * Poruka koja se salje kada su unete pogresne uloge.
     */
    public final static String NO_RULES_ADDED = "Potrebno je dodati uloge koje smeju da koriste resurs %s za metodu %s na putanji %s";
    /**
     * Poruka koja se salje kada nije prosledjen odgovarajuci ID.
     */
    public final static String NO_ID_PASSED = "Nije prosledjen odgovarajuci id";
    /**
     * Poruka koja se salje kada nije pronadjen nijedan user za odredjeni id.
     */
    public final static String NO_USER_FOUND_WITH_ID = "Nije pronadjen nijedan user za id: ";
    /**
     * Poruka koja se salje kada je ime obavezno polje za unos.
     */
    public final static String FIRST_NAME_VALIDATION = "Ime je obavezno polje za unos.";
    /**
     * Poruka koja se salje kada prezime obavezno polje za unos.
     */
    public final static String LAST_NAME_VALIDATION = "Prezime je obavezno polje za unos.";
    /**
     * Poruka koja se salje kada vec postoji korisnik za odredjeni username.
     */
    public final static String USERNAME_ALREADY_EXISTS = "Vec postoji korisnik za username: ";
    /**
     * Poruka koja se salje kada nije prosla validacija email-a.
     */
    public final static String EMAIL_VALIDATION = "Email mora biti u obliku nekiTekst@gmail.com gde nekiTekst sme sadrzati mala i velika slova, brojeve i specijalne karaktere: ., -, _.";
    /**
     * Poruka koja se salje kada header nije lepo konfigurisan.
     */
    public final static String NO_TOOKEN_IN_HEADER = "Header nije lepo konfigurisan - Token nije prosledjen!";
    /**
     * Poruka koja se salje kada nije unet komentar.
     */
    public final static String NO_TEXT_PRESENT = "Komentar je obavezna stavka prilikom recenzije materijala za ucenje.";
    /**
     * Poruka koja se salje kada ocena nije uneta.
     */
    public final static String NO_GRADE_PRESENT = "Ocena je obavezna stavka prilikom recenzije materijala za ucenje.";
    /**
     * Poruka koja se salje kada nije pronadjen materijal sa trazenim id-jem.
     */
    public final static String NO_LEARNING_MATERIAL_FOUND = "Nije pronadjen materijal sa id-jem: ";
    /**
     * Poruka koja se salje kada nije prosla validacija ocene.
     */
    public final static String GRADE_VALUE_VALIDATION = "Ocena mora da ima vrednost izmedju 1 i 10.";
    /**
     * Poruka koja se salje kada nije unet opis.
     */
    public final static String NO_LM_DESCRIPTION = "Opis je obavezno polje. ";
    /**
     * Poruka koja se salje kada nije unet link.
     */
    public final static String NO_LM_LINK = "Link je obavezno polje. ";
    /**
     * Poruka koja se salje kada nije uneta oblast.
     */
    public final static String NO_LM_AREA = "Oblast je obavezno polje. ";
    /**
     * Poruka koja se salje kada nije unet nivo.
     */
    public final static String NO_LM_LEVEL = "Nivo je obavezno polje. ";
    /**
     * Poruka koja se salje kada nije uneta platforma.
     */
    public final static String NO_LM_PLATFORM = "Platforma je obavezno polje. ";
    /**
     * Poruka koja se salje kada nije uneta tehnologija.
     */
    public final static String NO_LM_TECHNOLOGY = "Tehnologija je obavezno polje. ";
    /**
     * Poruka koja se salje kada nije unet tip.
     */
    public final static String NO_LM_CONTENT_TYPE = "Tip sadrzaja je obavezno polje. ";
    /**
     * Poruka koja se salje kada ne postoji oblast sa trazenim imenom.
     */
    public final static String NO_AREA_WITH_NAME = "Ne postoji oblast sa imenom: ";
    /**
     * Poruka koja se salje kada ne postoji platforma sa trazenim imenom.
     */
    public final static String NO_PLATFORM_WITH_NAME = "Ne postoji platforma sa imenom: ";
    /**
     * Poruka koja se salje kada ne postoji tehnologija sa trazenim imenom.
     */
    public final static String NO_TECHNOLOGY_WITH_NAME = "Ne postoji tehnologija sa imenom: ";
    /**
     * Poruka koja se salje kada ne postoji level sa trazenim imenom.
     */
    public final static String NO_LEVEL_WITH_NAME = "Ne postoji level sa imenom: ";
    /**
     * Poruka koja se salje kada ne postoji tip sadrzaja sa trazenim imenom.
     */
    public final static String NO_CONTENT_TYPE_WITH_NAME = "Ne postoji tip sadrzaja sa imenom: ";
    /**
     * Poruka koja se salje kada korisnik mora imati barem 1 ulogu u sistemu.
     */
    public final static String ROLE_VALIDATION = "Korisnik mora imati barem 1 ulogu u sistemu. ";
}
