package by.mikhalevich.grandcapitaltesttask.service.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;

import static by.mikhalevich.grandcapitaltesttask.service.util.Constants.EMAIL_PATTERN;

/**
 * ДТО-запрос для адресов электронной почты
 * @author Alex Mikhalevich
 * @created 2025-04-26 14:05
 */
public record EmailRequestDto(@Pattern(regexp = EMAIL_PATTERN, message = "Неверный формат email, пример: example@example.com")
                              @NotNull(message = "Не должен быть null")
                              String email) implements Serializable {
}
