package by.mikhalevich.grandcapitaltesttask.service.mapper;

import by.mikhalevich.grandcapitaltesttask.dao.model.User;
import by.mikhalevich.grandcapitaltesttask.service.dto.AuthenticationUserDto;
import org.springframework.stereotype.Component;

/**
 * Маппер для ДТО аутентификации
 * @author Alex Mikhalevich
 * @created 2025-04-26 16:09
 */
@Component
public class AuthenticationUserMapper {

    /**
     * Метод маппинга
     * @param user пользователь
     * @return ДТО-ответ для аутентификации
     */
    public AuthenticationUserDto userToAuthenticationUserDto(User user) {
        return new AuthenticationUserDto(String.valueOf(user.getId()));
    }
}
