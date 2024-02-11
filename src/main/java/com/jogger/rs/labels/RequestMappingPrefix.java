package com.jogger.rs.labels;

/**
 * Interfejs u kojem se nalaze nazivi resursa u aplikaciju.
 *
 * @author Jovan Virijevic
 */
public interface RequestMappingPrefix {
    /**
     * Prefiks koji se koristi za autentifikaciju.
     */
    public final static String AUTH = "/auth";
    /**
     * Prefiks koji se koristi za user-a.
     */
    public final static String USER = "/users";
    /**
     * Prefiks koji se koristi za materijale za ucenje.
     */
    public final static String LEARNING_MATERIAL = "/lm";
    /**
     * Prefiks koji se koristi za entitete materijala za ucenje: oblast, tip sadrzaja, tehnologija, nivo, platforma.
     */
    public final static String LEARNING_MATERIAL_ENTITIES = "/lm-entities";
    /**
     * Prefiks koji se koristi za komentare.
     */
    public final static String LEARNING_MATERIAL_COMMENT = "/lm-comments";

}
