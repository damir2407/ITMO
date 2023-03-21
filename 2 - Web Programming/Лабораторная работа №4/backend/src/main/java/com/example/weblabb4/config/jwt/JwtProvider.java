package com.example.weblabb4.config.jwt;

import io.jsonwebtoken.*;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * Класс для генерации и валидации токена
 */
@Log
@Component
public class JwtProvider {

    @Value("$(jwt.secret)")
    private String jwtSecret;

    /**
     * signWith — принимает на вход алгоритм подписи и кодовое слово, которое потом потребуется для расшифровки.
     *
     * @param username
     * @return токен
     */
    public String generateToken(String username) {
        Date date = Date.from(LocalDate.now().plusDays(15).atStartOfDay(ZoneId.systemDefault()).toInstant());
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    /**
     * для валидации поступающего токена
     *
     * @param token
     * @return bool
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException expiredJwtException) {
            log.info("Expired JWT token.");
        } catch (UnsupportedJwtException unsupportedJwtException) {
            log.info("Unsupported JWT token.");
        } catch (MalformedJwtException malformedJwtException) {
            log.info("Invalid JWT token.");
        } catch (SignatureException signatureException) {
            log.info("Invalid JWT signature.");
        } catch (IllegalArgumentException illegalArgumentException) {
            log.info("JWT token compact of handler are invalid.");
        }
        return false;
    }

    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

}
