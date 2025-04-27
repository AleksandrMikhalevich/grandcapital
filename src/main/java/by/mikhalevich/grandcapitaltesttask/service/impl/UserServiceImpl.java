package by.mikhalevich.grandcapitaltesttask.service.impl;

import by.mikhalevich.grandcapitaltesttask.dao.model.User;
import by.mikhalevich.grandcapitaltesttask.dao.repository.UserRepository;
import by.mikhalevich.grandcapitaltesttask.service.dto.UserSearchResponseDto;
import by.mikhalevich.grandcapitaltesttask.service.intrfc.UserService;
import by.mikhalevich.grandcapitaltesttask.service.mapper.UserSearchMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

/**
 * Класс-сервис для работы с пользователями
 * @author Alex Mikhalevich
 * @created 2025-04-27 13:51
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    /**
     * Репозиторий пользователя
     */
    private final UserRepository userRepository;

    @Override
    public Page<UserSearchResponseDto> searchUsers(Specification<User> withFilters, Pageable pageable) {
        Page<User> users = userRepository.findAll(withFilters, pageable);
        return UserSearchMapper.toDtoPage(users);
    }
}
