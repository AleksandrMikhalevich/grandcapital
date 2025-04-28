package by.mikhalevich.grandcapitaltesttask.service.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Класс-фильтр для JWT-токена
 * @author Alex Mikhalevich
 * @created 2025-04-26 16:06
 */
@RequiredArgsConstructor
@Slf4j
public class JwtTokenFilter extends OncePerRequestFilter {

    protected static final List<String> AUTH_WHITELIST = Arrays.asList(
            "/api/v1/auth/login",
            "/actuator/**",
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/v3/api-docs/**",
            "/webjars/**"
    );

    /**
     * Класс-провайдер для JWT-токена
     */
    private final JwtTokenProvider jwtTokenProvider;

    private static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    /**
     * Добавление фильтра по JWT-токену
     */
    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain filterChain) throws ServletException, IOException {
        String servletPath = req.getServletPath();
        boolean isWhitelisted = AUTH_WHITELIST.stream()
                .anyMatch(pattern -> PATH_MATCHER.match(pattern, servletPath));
        log.info("Путь: {}. В белом списке={}", servletPath, isWhitelisted);
        if (isWhitelisted) {
            filterChain.doFilter(req, res);
            return;
        }
        String token = jwtTokenProvider.resolveToken(req);
        if (token != null && jwtTokenProvider.validateToken(token)) {
            Authentication auth = jwtTokenProvider.getAuthentication(token);
            if (auth != null) {
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        filterChain.doFilter(req, res);
    }
}
