package com.sp.ordercreationservice;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class KeycloakJwtAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    private String clientApp;

    public KeycloakJwtAuthenticationConverter(String clientApp) {
        this.clientApp = clientApp;
    }
    private final Converter<Jwt, Collection<GrantedAuthority>> delegate = new JwtGrantedAuthoritiesConverter();

    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {
        List<GrantedAuthority> authorityList = extractRoles(jwt);
        Collection<GrantedAuthority> authorities = delegate.convert(jwt);
        if (authorities != null) {
            authorityList.addAll(authorities);
        }
        return new JwtAuthenticationToken(jwt, authorityList);
    }

    private List<GrantedAuthority> extractRoles(Jwt jwt) {
        Map<String,Object> realmAccess = (Map<String, Object>) jwt.getClaims().get("realm_access");
        Map<String,Object> resourceAccess = (Map<String, Object>) jwt.getClaims().get("resource_access");
        Map<String,Object> appRoles = (Map<String, Object>) resourceAccess.get(clientApp);
        List<String> roles =  (List<String>) realmAccess.get("roles");
        roles.addAll((List<String>) appRoles.get("roles"));

        return roles.stream()
                .filter(role -> role.startsWith("ROLE_"))
                .map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }
}
