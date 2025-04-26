package by.mikhalevich.grandcapitaltesttask.service.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;

import static by.mikhalevich.grandcapitaltesttask.service.util.ValidationConstants.EMAIL_PATTERN;

/**
 * ДТО-запрос для адресов электронной почты
 * @author Alex Mikhalevich
 * @created 2025-04-26 14:05
 */
public record EmailRequestDto(@Pattern(regexp = EMAIL_PATTERN, message = "Неверный формат адреса электронной почты")
                              @NotNull(message = "Не должно быть null")
                              String email) implements Serializable {
}
