package by.mikhalevich.grandcapitaltesttask.service.dto;

import java.io.Serializable;

/**
 * ДТО-ответ для аутентификации
 * @author Alex Mikhalevich
 * @created 2025-04-26 15:09
 */
public record AuthenticationUserDto(String userId) implements Serializable {
}
