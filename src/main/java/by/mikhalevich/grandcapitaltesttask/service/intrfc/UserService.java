package by.mikhalevich.grandcapitaltesttask.service.intrfc;

import by.mikhalevich.grandcapitaltesttask.dao.model.User;
import by.mikhalevich.grandcapitaltesttask.service.dto.UserSearchResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

/**
 * Интерфейс-сервис для работы с пользователями
 * @author Alex Mikhalevich
 * @created 2025-04-27 13:50
 */
public interface UserService {

    /**
     * Ищет пользователей с фильтрацией и пагинацией
     * @param withFilters фильтр
     * @param pageable пагинация
     * @return результат
     */
    Page<UserSearchResponseDto> searchUsers(Specification<User> withFilters, Pageable pageable);
}
