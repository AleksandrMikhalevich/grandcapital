package by.mikhalevich.grandcapitaltesttask.service.mapper;

import by.mikhalevich.grandcapitaltesttask.dao.model.User;
import by.mikhalevich.grandcapitaltesttask.service.dto.UserSearchResponseDto;
import org.springframework.data.domain.Page;

/**
 * Маппер для ДТО при поиске пользователей
 * @author Alex Mikhalevich
 * @created 2025-04-27 14:34
 */
public class UserSearchMapper {

    private UserSearchMapper() {
    }

    /**
     * Метод маппинга
     * @param user пользователь
     * @return ДТО-ответ при поиске
     */
    public static UserSearchResponseDto toDto(User user) {
        if (user == null) {
            return null;
        }
        return new UserSearchResponseDto(user.getName(), user.getDateOfBirth());
    }

    /**
     * Метод маппинга Page<User> в Page<UserSearchResponseDto>
     * @param userPage найденные пользователи
     * @return ДТО-ответ при поиске
     */
    public static Page<UserSearchResponseDto> toDtoPage(Page<User> userPage) {
        return userPage.map(UserSearchMapper::toDto);
    }
}
