package by.mikhalevich.grandcapitaltesttask.api.exception;

/**
 * Класс-исключение при проверке наличия пользователя
 * @author Alex Mikhalevich
 * @created 2025-04-26 13:40
 */
public class DataNotFoundException extends RuntimeException {

    public DataNotFoundException(String message) {
        super(message);
    }
}
