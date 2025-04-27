package by.mikhalevich.grandcapitaltesttask.api.controller;

import by.mikhalevich.grandcapitaltesttask.service.dto.AuthenticationRequestDto;
import by.mikhalevich.grandcapitaltesttask.service.dto.AuthenticationUserDto;
import by.mikhalevich.grandcapitaltesttask.service.impl.AuthenticationServiceImpl;
import by.mikhalevich.grandcapitaltesttask.service.security.jwt.JwtTokenProvider;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static by.mikhalevich.grandcapitaltesttask.service.util.Constants.AUTH_ERROR_MESSAGE;
import static by.mikhalevich.grandcapitaltesttask.service.util.Constants.LOG_AUTH_ERROR_MESSAGE;

/**
 * Класс-контроллер для работы с JWT-аутентификацией
 * @author Alex Mikhalevich
 * @created 2025-04-26 15:54
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth/")
@Slf4j
public class AuthenticationController {

    /**
     * Провайдер JWT-токена
     */
    private final JwtTokenProvider jwtTokenProvider;

    /**
     * Сервис аутентификации
     */
    private final AuthenticationServiceImpl authenticationService;

    /**
     * Аутентификация пользователя по email + пароль
     * @param requestDto запрос email + пароль
     * @return ответ
     */
    @PostMapping("login")
    public ResponseEntity<Object> login(@Valid @RequestBody AuthenticationRequestDto requestDto) {
        AuthenticationUserDto user = authenticationService.findByEmailAndPassword(requestDto);
        if (user == null) {
            log.info(LOG_AUTH_ERROR_MESSAGE, requestDto.email());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(AUTH_ERROR_MESSAGE);
        }
        Map<Object, Object> response = new HashMap<>();
        response.put("userId", user.userId());
        response.put("token", jwtTokenProvider.createToken(user));
        return ResponseEntity.ok(response);
    }
}
