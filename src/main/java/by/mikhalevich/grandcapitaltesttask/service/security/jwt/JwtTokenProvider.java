package by.mikhalevich.grandcapitaltesttask.service.security.jwt;

import by.mikhalevich.grandcapitaltesttask.service.dto.AuthenticationUserDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Instant;
import java.util.Date;

/**
 * Класс-провайдер для JWT-токена
 * @author Alex Mikhalevich
 * @created 2025-04-26 16:05
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class JwtTokenProvider {

    /**
     * Фраза-секрет
     */
    @Value("${spring.security.jwt.secret}")
    private String secret;

    /**
     * Время валидности токена в мс
     */
    @Value("${spring.security.jwt.expired}")
    private long validityInMilliseconds;

    /**
     * Сервис нахождения информации о пользователе
     */
    private final JwtUserDetailsService userDetailsService;

    /**
     * Создание ключа
     */
    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(this.secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * Создание токена
     */
    public String createToken(AuthenticationUserDto user) {
        Claims claims = Jwts.claims().setSubject(user.userId());
        Instant now = Instant.now();
        Instant validity = now.plusMillis(validityInMilliseconds);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(validity))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Получение аутентификации
     */
    public Authentication getAuthentication(String token) {
        JwtUser jwtUser = this.userDetailsService.loadUserById(getUserId(token));
        return new UsernamePasswordAuthenticationToken(jwtUser, "", jwtUser.getAuthorities());
    }

    /**
     * Получение Id пользователя из токена
     */
    public Long getUserId(String token) {
        return Long.valueOf(Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody().getSubject());
    }

    /**
     * Разрешение токена
     */
    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader(HttpHeaders.AUTHORIZATION);
        if (bearerToken != null && bearerToken.startsWith("Bearer")) {
            return bearerToken.substring(6);
        }
        return null;
    }

    /**
     * Валидация токена
     */
    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token);
            if (claims.getBody().getExpiration().before(new Date())) {
                log.info("JWT токен истек");
                return false;
            }
            log.info("JWT токен валиден");
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            log.info("Истекший или невалидный токен");
            return false;
        }
    }
}
