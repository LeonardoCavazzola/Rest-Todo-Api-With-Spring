package com.exemple.api.app.common.helper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTHelper {

    @Value("${forum.jwt.duration}")
    private String duration;

    @Value("${forum.jwt.secret}")
    private String secret;

    public String generate(Long subject) {
        Date today = new Date();
        Date expiration = new Date(today.getTime() + Long.parseLong(duration));

        return Jwts.builder()
                .setSubject(subject.toString())
                .setIssuedAt(today)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public Boolean isValid(String token) {
        try {
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Long getSubject(String token) {
        String subject = Jwts.parser()
                .setSigningKey(this.secret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();

        return Long.parseLong(subject);
    }
}
