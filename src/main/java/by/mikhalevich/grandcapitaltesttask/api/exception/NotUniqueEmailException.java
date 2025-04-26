package by.mikhalevich.grandcapitaltesttask.api.exception;

/**
 * Класс-исключение при проверке уникальности адресов электронной почты
 * @author Alex Mikhalevich
 * @created 2025-04-26 13:35
 */
public class NotUniqueEmailException extends RuntimeException{

    public NotUniqueEmailException(String message) {
        super(message);
    }
}
