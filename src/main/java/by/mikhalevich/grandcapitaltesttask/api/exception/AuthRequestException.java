package by.mikhalevich.grandcapitaltesttask.api.exception;

/**
 * Класс-исключение при аутентификации пользователя
 * @author Alex Mikhalevich
 * @created 2025-04-26 16:11
 */
public class AuthRequestException extends RuntimeException {

    public AuthRequestException(String message) {
        super(message);
    }
}
