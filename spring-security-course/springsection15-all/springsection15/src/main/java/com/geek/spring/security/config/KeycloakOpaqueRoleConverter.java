package com.geek.spring.security.config;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenAuthenticationConverter;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class KeycloakOpaqueRoleConverter implements OpaqueTokenAuthenticationConverter {
    /**
     * introspectedToken - the opaque token (Client will send this to resource server for further validation with Auth Server)
     *  by invoking introspection endpoint of Auth Server
     *
     * authenticatedPrincipal - the introspection response
     *
     */
    @Override
    public Authentication convert(String introspectedToken, OAuth2AuthenticatedPrincipal authenticatedPrincipal) {
        String username = authenticatedPrincipal.getAttribute("preferred_username");
        Map<String, Object> realmAccess = authenticatedPrincipal.getAttribute("realm_access");
        Collection<GrantedAuthority> roles = ((List<String>) realmAccess.get("roles"))
                .stream().map(roleName -> "ROLE_" + roleName)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        return new UsernamePasswordAuthenticationToken(authenticatedPrincipal.getName(), null, roles);
    }
}
