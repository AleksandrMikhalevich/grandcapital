package by.mikhalevich.grandcapitaltesttask.service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;

import static by.mikhalevich.grandcapitaltesttask.service.util.Constants.EMAIL_PATTERN;

/**
 * ДТО-запрос для аутентификации
 * @author Alex Mikhalevich
 * @created 2025-04-26 15:11
 */
public record AuthenticationRequestDto(
        @NotBlank(message = "Email обязателен")
        @Pattern(regexp = EMAIL_PATTERN, message = "Неверный email")
        String email,

        @NotBlank(message = "Пароль обязателен")
        String password) implements Serializable {
}
