package com.jogger.rs.email.service;

public interface EmailLabels {

    public final static String FROM = "joggertest123@gmail.com";
    public final static String SUBJECT = "Kreiran nalog";
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
