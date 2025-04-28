package by.mikhalevich.grandcapitaltesttask.api.handler;

import by.mikhalevich.grandcapitaltesttask.api.exception.DataNotFoundException;
import by.mikhalevich.grandcapitaltesttask.api.exception.ErrorResponse;
import by.mikhalevich.grandcapitaltesttask.api.exception.MoneyTransferException;
import by.mikhalevich.grandcapitaltesttask.api.exception.NotUniqueDataException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Обработчик исключений
 * @author Alex Mikhalevich
 * @created 2025-04-27 12:03
 */
@RestControllerAdvice
public class AppExceptionHandler {

    /**
     * Обработка {@link DataNotFoundException}
     */
    @ExceptionHandler({DataNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handlerDataNotFoundException(DataNotFoundException exception) {
        return new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                exception.getMessage());
    }

    /**
     * Обработка {@link NotUniqueDataException}
     */
    @ExceptionHandler({NotUniqueDataException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handlerNotUniqueDataException(NotUniqueDataException exception) {
        return new ErrorResponse(
                HttpStatus.CONFLICT.value(),
                exception.getMessage());
    }

    /**
     * Обработка {@link MoneyTransferException}
     */
    @ExceptionHandler({MoneyTransferException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handlerMoneyTransferException(MoneyTransferException exception) {
        return new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                exception.getMessage());
    }
}
