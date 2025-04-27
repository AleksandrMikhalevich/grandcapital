package by.mikhalevich.grandcapitaltesttask.service.impl;

import by.mikhalevich.grandcapitaltesttask.dao.model.User;
import by.mikhalevich.grandcapitaltesttask.dao.repository.UserRepository;
import by.mikhalevich.grandcapitaltesttask.service.dto.AuthenticationRequestDto;
import by.mikhalevich.grandcapitaltesttask.service.dto.AuthenticationUserDto;
import by.mikhalevich.grandcapitaltesttask.service.intrfc.AuthenticationService;
import by.mikhalevich.grandcapitaltesttask.service.mapper.AuthenticationUserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static by.mikhalevich.grandcapitaltesttask.service.util.Constants.LOG_USER_FOUND_MESSAGE;
import static by.mikhalevich.grandcapitaltesttask.service.util.Constants.LOG_USER_NOT_FOUND_MESSAGE;

/**
 * Класс-сервис для аутентификации
 * @author Alex Mikhalevich
 * @created 2025-04-26 15:55
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    /**
     * Репозиторий пользователя
     */
    private final UserRepository userRepository;

    /**
     * Маппер ДТО
     */
    private final AuthenticationUserMapper authenticationUserMapper;

    /**
     * BCrypt-кодировщик
     */
    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthenticationUserDto findByEmailAndPassword(AuthenticationRequestDto requestDto) {
        User user = userRepository.findUserByEmail(requestDto.email());
        if (user != null && passwordEncoder.matches(requestDto.password(), user.getPassword())) {
            log.info(LOG_USER_FOUND_MESSAGE, requestDto.email());
            return authenticationUserMapper.userToAuthenticationUserDto(user);
        }
        log.info(LOG_USER_NOT_FOUND_MESSAGE, requestDto.email());
        return null;
    }
}
