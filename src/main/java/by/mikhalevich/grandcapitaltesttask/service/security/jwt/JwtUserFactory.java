package by.mikhalevich.grandcapitaltesttask.service.security.jwt;

import by.mikhalevich.grandcapitaltesttask.dao.model.User;

/**
 * Класс-фабрика для JWT-аутентификации
 * @author Alex Mikhalevich
 * @created 2025-04-26 16:10
 */
public final class JwtUserFactory {

    private JwtUserFactory() {

    }

    /**
     * Создание JWT-пользователя
     */
    public static JwtUser create(User user) {
        return new JwtUser(user.getId(), user.getName(), user.getPassword());
    }

}
