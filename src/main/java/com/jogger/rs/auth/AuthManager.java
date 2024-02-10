package com.jogger.rs.auth;

import com.jogger.rs.labels.ErrorMessage;
import com.jogger.rs.labels.RequestMappingPrefix;
import com.jogger.rs.role.Role;
import com.jogger.rs.role.RoleName;
import com.jogger.rs.user.UserSession;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.security.sasl.AuthenticationException;
import java.util.*;

/**
 * Servis ciji je zadatak da autentifikuje korisnika i autorizuje korisnicke zahteve
 *
 * @author Jovan Virijevic
 */
@CommonsLog
@Component
public class AuthManager {

    /**
     * Prefiks za svaku putanju (cita se iz application.properties fajla)
     */
    @Value("${server.servlet.context-path}")
    private String contextPath;

    /**
     * Mapa pravila - resursi i uloge koje smeju da pristupe tim resursima
     */
    private HashMap<String, List<RoleName>> authRules = new HashMap<>();

    /**
     * Servis koji vodi racuna o korisnickim sesijama
     */
    private SessionManager sessionManager;

    /**
     * Javni konstruktor
     *
     * @param sessionManager servis koji vodi racuna o korisnickim sesijama
     */
    @Autowired
    public AuthManager(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
        addAuthRules();
    }

    /**
     * Metoda koja postavlja pravila za koriscenje resursa aplikacije, odnosno autorizaciju korisnickih zahteva
     */
    private void addAuthRules() {
        // ovde cemo da definisemo pravila koji request mora da ima koju ulogu
        authRules.put(HttpMethod.POST.name() + "-" + RequestMappingPrefix.AUTH,
                Collections.emptyList());
        authRules.put(HttpMethod.GET.name() + "-" + RequestMappingPrefix.USER,
                List.of(RoleName.Admin));
        authRules.put(HttpMethod.DELETE.name() + "-" + RequestMappingPrefix.USER,
                List.of(RoleName.Admin));
        authRules.put(HttpMethod.POST.name() + "-" + RequestMappingPrefix.USER,
                List.of(RoleName.Admin));
        authRules.put(HttpMethod.PUT.name() + "-" + RequestMappingPrefix.USER,
                List.of(RoleName.Admin));
        authRules.put(HttpMethod.GET.name() + "-" + RequestMappingPrefix.LEARNING_MATERIAL,
                List.of(RoleName.Intern, RoleName.Developer));
        authRules.put(HttpMethod.GET.name() + "-" + RequestMappingPrefix.LEARNING_MATERIAL_ENTITIES,
                List.of(RoleName.Intern, RoleName.Developer));
        authRules.put(HttpMethod.POST.name() + "-" + RequestMappingPrefix.LEARNING_MATERIAL_COMMENT,
                List.of(RoleName.Developer));
        authRules.put(HttpMethod.DELETE.name() + "-" + RequestMappingPrefix.LEARNING_MATERIAL,
                List.of(RoleName.Developer));
        authRules.put(HttpMethod.POST.name() + "-" + RequestMappingPrefix.LEARNING_MATERIAL,
                List.of(RoleName.Developer));

    }

    /**
     * Metoda koji vrsi autorizaciju korisnickog zahteva
     *
     * @param request korisnicki zahtev
     * @return
     * <ul>
     *     <li> true - ako korisnik sme da pristupi resursu na putanji </li>
     *     <li> false - ako korisnik pokusava da pristupi nekoj putanji za koju nisu definisana pravila</li>
     * </ul>
     * @throws AuthenticationException ako korisnik nema pravo da pristupi resursu na zadatoj putanji
     */
    public Boolean auth(HttpServletRequest request) throws AuthenticationException {
        if (authRules == null) addAuthRules();
        if ((contextPath + RequestMappingPrefix.AUTH + "/login").equals(request.getRequestURI())) return true; // login dozvoljavamo svima
        String token = request.getHeader("Token");
        if (!StringUtils.hasText(token))
            throw new AuthenticationException(ErrorMessage.NO_TOOKEN_IN_HEADER);
        UserSession user = sessionManager.getUserFromSession(token)
                .orElseThrow(() -> new AuthenticationException(ErrorMessage.NO_TOKEN_FOUND + token));
        String method = request.getMethod();
        String requestMapping = extractRequestMappingFromRequest(request);
        List<RoleName> roles = authRules.get(method + "-" + requestMapping);
        if (roles == null) {
            log.error(String.format(ErrorMessage.NO_RULES_ADDED, requestMapping, method, request.getRequestURI()));
            return false;
        }
        if (roles.isEmpty()) return true; // prazna lista znaci da je svima dozvolje da pristupi resursu, bez obzira na ulogu
        return checkIfUserHasNecessaryRoles(user, roles);
    }

    /**
     * Metoda koja izvlaci naziv resursa kojem korisnik pokusava da pristupi
     *
     * @param request korisnicki zahtev
     * @return naziv resursa kojem korisnik pokusava da pristupi
     */
    private String extractRequestMappingFromRequest(HttpServletRequest request) {
        String uri = request.getRequestURI();
        String tmp = uri.substring(contextPath.length());
        return tmp.indexOf("/", 1) == -1 ? tmp : tmp.substring(0, tmp.indexOf("/", 1));
    }

    /**
     * Metoda koja proverava da li korisnik ima potrebne uloge da pristupi resursu na zadatoj putanji
     *
     * @param user korisnik koji pokusava da pristupi resursu na zadatoj putanji
     * @param roles uloge koje korisnik mora da ima da bi pristupio resursu na zadatoj putanji
     * @return
     * <ul>
     *     <li> true - ako korisnik ima uloge potrebne da pristupi resursu na zadatoj putanji </li>
     *     <li> false - ako korisnik nema uloge potrebne da pristupi resursu na zadatoj putanji </li>
     * </ul>
     */
    private Boolean checkIfUserHasNecessaryRoles(UserSession user, List<RoleName> roles) {
        for (Role role : user.getRoles()) {
            if (roles.contains(role.getName())) return true;
        }
        return false;
    }
}
