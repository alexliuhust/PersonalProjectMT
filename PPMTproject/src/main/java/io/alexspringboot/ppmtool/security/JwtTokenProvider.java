package io.alexspringboot.ppmtool.security;

import io.alexspringboot.ppmtool.domain.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static io.alexspringboot.ppmtool.security.SecurityConstants.EXPIRATION_TIME;
import static io.alexspringboot.ppmtool.security.SecurityConstants.SECRET;

@Component
public class JwtTokenProvider {

    // Generate the token
    public String generateToken(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        String userId = Long.toString(user.getId());

        Date now = new Date(System.currentTimeMillis());
        Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);

        Map<String, Object> claims = new HashMap<>();
        claims.put("id", (Long.toString(user.getId())));
        claims.put("username", user.getUsername());
        claims.put("fullName", user.getFullName());

        return Jwts.builder()
                .setSubject(userId)
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    // Validate the token


    // Get user id from the token


}
