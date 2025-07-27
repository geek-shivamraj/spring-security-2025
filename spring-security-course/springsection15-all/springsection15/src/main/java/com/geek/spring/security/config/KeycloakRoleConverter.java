package com.geek.spring.security.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * The Responsibility of generating the JWT Access Token is with the Keycloak & in the JWT token itself,
 *  we will have various information related to the end user like username, email, roles etc.
 *
 *  The Purpose of this class is to extract the roles from the JWT token and convert them the roles info in the form of SimpleGrantedAuthority
 *  becoz Spring Security framework can only understand the roles & authorities in the form of SimpleGrantedAuthority
 */
public class KeycloakRoleConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    @Override
    public Collection<GrantedAuthority> convert(Jwt source) {
        Map<String, Object> realmAccess = (Map<String, Object>) source.getClaims().get("realm_access");

        if(realmAccess == null || realmAccess.isEmpty()) {
            return new ArrayList<>();
        }

        Collection<GrantedAuthority> returnValue = ((List<String>) realmAccess.get("roles"))
                .stream().map(roleName -> "ROLE_" + roleName)
                .map(SimpleGrantedAuthority::new).collect(Collectors.toList());

        return returnValue;
    }
}
