package by.mikhalevich.grandcapitaltesttask.api.exception;

/**
 * Общий ответ на ошибку
 * @author Alex Mikhalevich
 * @created 2025-04-27 12:02
 */
public record ErrorResponse(int statusCode, String message) {
}

