package by.mikhalevich.grandcapitaltesttask.service.intrfc;

import by.mikhalevich.grandcapitaltesttask.service.dto.AuthenticationRequestDto;
import by.mikhalevich.grandcapitaltesttask.service.dto.AuthenticationUserDto;

/**
 * Интерфейс-сервис для аутентификации
 * @author Alex Mikhalevich
 * @created 2025-04-26 15:10
 */
public interface AuthenticationService {

    /**
     * Аутентификация пользователя по email + пароль
     * @param requestDto ДТО-запрос для аутентификации
     * @return ДТО-ответ для аутентификации
     */
    AuthenticationUserDto findByEmailAndPassword(AuthenticationRequestDto requestDto);
}
