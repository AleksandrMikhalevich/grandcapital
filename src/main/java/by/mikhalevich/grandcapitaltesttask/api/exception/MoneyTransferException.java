package by.mikhalevich.grandcapitaltesttask.api.exception;

/**
 * Класс-исключение при проверке операции перевода средств
 * @author Alex Mikhalevich
 * @created 2025-04-28 10:44
 */
public class MoneyTransferException extends RuntimeException {

    public MoneyTransferException(String message) {
        super(message);
    }
}
