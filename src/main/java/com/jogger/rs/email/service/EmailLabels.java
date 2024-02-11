package com.jogger.rs.email.service;

/**
 * Klasa u kojoj se nalaze labele vezane za slanje email poruka.
 *
 * @author Jovan Virijevic
 */
public interface EmailLabels {

    /**
     * Email nalog sa kojeg se salju email poruke.
     */
    public final static String FROM = "joggertest123@gmail.com";

    /**
     * Naslov email-a.
     */
    public final static String SUBJECT = "Kreiran nalog";

    /**
     * Telo email-a.
     */
    public final static String BODY = """
            Postovana/Postovani,
            
            Uspesno Vam je kreiran nalog na Jogger platformi.
            Username: %s
            Lozinka: %s
            
            Molimo Vas da ovu poruku i njen sadrzaj smatrate poverljivim.
            
            Srdacan pozdrav,
            
            Vas Jogger tim.
            """;

}
