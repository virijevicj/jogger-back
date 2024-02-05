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

@CommonsLog
@Component
public class AuthManager {

    @Value("${server.servlet.context-path}")
    private String contextPath;
    private HashMap<String, List<RoleName>> authRules = new HashMap<>();
    private SessionManager sessionManager;

    @Autowired
    public AuthManager(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
        addAuthRules();
    }

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
                List.of(RoleName.Developer));
        authRules.put(HttpMethod.GET.name() + "-" + RequestMappingPrefix.LEARNING_MATERIAL_ENTITIES,
                List.of(RoleName.Developer));
        authRules.put(HttpMethod.POST.name() + "-" + RequestMappingPrefix.LEARNING_MATERIAL_COMMENT,
                List.of(RoleName.Developer));

    }

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

    private String extractRequestMappingFromRequest(HttpServletRequest request) {
        String uri = request.getRequestURI();
        return uri.substring(contextPath.length());
    }

    private Boolean checkIfUserHasNecessaryRoles(UserSession user, List<RoleName> roles) {
        for (Role role : user.getRoles()) {
            if (roles.contains(role.getName())) return true;
        }
        return false;
    }
}
