package by.mikhalevich.grandcapitaltesttask.service.dto;

import java.time.LocalDate;

/**
 * ДТО-ответ при поиске пользователей
 * @author Alex Mikhalevich
 * @created 2025-04-27 13:44
 */
public record UserSearchResponseDto(String name, LocalDate dateOfBirth) {

}
