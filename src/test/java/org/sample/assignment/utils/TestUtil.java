package org.sample.assignment.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * @author RahulPure
 */
public class TestUtil {
    public static void successResponseEntity(ResponseEntity responseEntity) {
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
    public static String generateId(String prefix) {
        if (Objects.isNull(prefix)) return UUID.randomUUID().toString();
        return prefix.concat("-").concat(UUID.randomUUID().toString());
    }
    public static Authentication getAuthentication() {
        return new Authentication() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return null;
            }

            @Override
            public Object getCredentials() {
                return null;
            }

            @Override
            public Object getDetails() {
                return null;
            }

            @Override
            public Object getPrincipal() {
                return null;
            }

            @Override
            public boolean isAuthenticated() {
                return true;
            }

            @Override
            public String getName() {
                return "123-sdfs-45435";
            }

            @Override
            public void setAuthenticated(boolean b) throws IllegalArgumentException {}
        };
    }
}
