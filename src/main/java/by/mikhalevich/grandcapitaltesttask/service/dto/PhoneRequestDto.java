package by.mikhalevich.grandcapitaltesttask.service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import static by.mikhalevich.grandcapitaltesttask.service.util.Constants.PHONE_PATTERN;

/**
 * @author Alex Mikhalevich
 * @created 2025-04-27 13:23
 */
public record PhoneRequestDto(@Pattern(regexp = PHONE_PATTERN, message = "Неверный формат номера телефона, пример: 79207865432")
                              @NotBlank(message = "Не ложен быть пустым")
                              String phone) {
}
