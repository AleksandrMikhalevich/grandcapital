package by.mikhalevich.grandcapitaltesttask.service.security.jwt;

import by.mikhalevich.grandcapitaltesttask.dao.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Класс-сервис для JWT-аутентификации
 * @author Alex Mikhalevich
 * @created 2025-04-26 16:08
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class JwtUserDetailsService {

    /**
     * Репозиторий пользователя
     */
    private final UserRepository userRepository;

    /**
     * Нахождение пользователя по Id
     */
    public UserDetails loadUserById(Long id) throws UsernameNotFoundException {
        return userRepository.findById(id)
                .map(JwtUserFactory::create)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Пользователь с Id %s не найден", id)));
    }
}
