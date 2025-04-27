package by.mikhalevich.grandcapitaltesttask.service.impl;

import by.mikhalevich.grandcapitaltesttask.api.exception.DataNotFoundException;
import by.mikhalevich.grandcapitaltesttask.api.exception.NotUniqueDataException;
import by.mikhalevich.grandcapitaltesttask.dao.model.EmailData;
import by.mikhalevich.grandcapitaltesttask.dao.model.User;
import by.mikhalevich.grandcapitaltesttask.dao.repository.EmailDataRepository;
import by.mikhalevich.grandcapitaltesttask.dao.repository.UserRepository;
import by.mikhalevich.grandcapitaltesttask.service.intrfc.EmailDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static by.mikhalevich.grandcapitaltesttask.service.util.Constants.*;

/**
 * Класс-сервис для работы с адресами электронной почты пользователя
 * @author Alex Mikhalevich
 * @created 2025-04-26 13:31
 */
@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class EmailDataServiceImpl implements EmailDataService {

    /**
     * Репозиторий пользователя
     */
    private final UserRepository userRepository;

    /**
     * Репозиторий адресов электронной почты
     */
    private final EmailDataRepository emailDataRepository;

    @Override
    public void addEmailForUser(Long userId, String email) {
        if (emailDataRepository.findByEmail(email).isPresent()) {
            log.info(LOG_NOT_UNIQUE_EMAIL_ERROR_MESSAGE, email);
            throw new NotUniqueDataException(String.format(NOT_UNIQUE_EMAIL_ERROR_MESSAGE, email));
        }
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new DataNotFoundException(String.format(USER_ID_NOT_FOUND_MESSAGE, userId)));
        EmailData emailData = EmailData.builder()
                .email(email)
                .user(user)
                .build();
        user.getEmailData().add(emailData);
        userRepository.save(user);
    }

    @Override
    public void updateUserEmail(Long userId, Long emailDataId, String newEmail) {
        if (emailDataRepository.findByEmail(newEmail).isPresent()) {
            log.info(LOG_NOT_UNIQUE_EMAIL_ERROR_MESSAGE, newEmail);
            throw new NotUniqueDataException(String.format(NOT_UNIQUE_EMAIL_ERROR_MESSAGE, newEmail));
        }
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new DataNotFoundException(String.format(USER_ID_NOT_FOUND_MESSAGE, userId)));
        EmailData emailData = user.getEmailData().stream()
                .filter(ed -> ed.getId().equals(emailDataId))
                .findFirst()
                .orElseThrow(() -> new DataNotFoundException(String.format(EMAIL_NOT_FOUND_MESSAGE, userId)));
        emailData.setEmail(newEmail);
        userRepository.save(user);
    }

    @Override
    public void deleteUserEmail(Long userId, Long emailDataId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new DataNotFoundException(String.format(USER_ID_NOT_FOUND_MESSAGE, userId)));
        boolean removed = user.getEmailData()
                .removeIf(ed -> ed.getId().equals(emailDataId));
        if (!removed) {
            log.info(LOG_EMAIL_ID_NOT_FOUND_MESSAGE, emailDataId, userId);
            throw new DataNotFoundException(String.format(EMAIL_NOT_FOUND_MESSAGE, userId));
        }
        userRepository.save(user);
    }
}
