package by.mikhalevich.grandcapitaltesttask.service.impl;

import by.mikhalevich.grandcapitaltesttask.api.exception.DataNotFoundException;
import by.mikhalevich.grandcapitaltesttask.api.exception.NotUniqueEmailException;
import by.mikhalevich.grandcapitaltesttask.dao.model.EmailData;
import by.mikhalevich.grandcapitaltesttask.dao.model.User;
import by.mikhalevich.grandcapitaltesttask.dao.repository.EmailDataRepository;
import by.mikhalevich.grandcapitaltesttask.dao.repository.UserRepository;
import by.mikhalevich.grandcapitaltesttask.service.intrfc.EmailDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Класс-сервис для работы с адресами электронной почты пользователя
 * @author Alex Mikhalevich
 * @created 2025-04-26 13:31
 */
@Service
@Transactional
@RequiredArgsConstructor
public class EmailDataServiceImpl implements EmailDataService {

    private final UserRepository userRepository;

    private final EmailDataRepository emailDataRepository;

    @Override
    public void addEmailForUser(Long userId, String email) {
        // Проверка уникальности email
        if (emailDataRepository.findByEmail(email).isPresent()) {
            throw new NotUniqueEmailException(String.format("Email %s уже используется другим пользователем", email));
        }
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new DataNotFoundException(String.format("Пользователь c Id %s не найден", userId)));
        EmailData emailData = EmailData.builder()
                .email(email)
                .user(user)
                .build();
        user.getEmailData().add(emailData);
        userRepository.save(user);
    }

    @Override
    public void updateUserEmail(Long userId, Long emailDataId, String newEmail) {
        // Проверка уникальности нового email
        if (emailDataRepository.findByEmail(newEmail).isPresent()) {
            throw new NotUniqueEmailException(String.format("Email %s уже используется другим пользователем", newEmail));
        }
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new DataNotFoundException(String.format("Пользователь c Id %s не найден", userId)));
        EmailData emailData = user.getEmailData().stream()
                .filter(ed -> ed.getId().equals(emailDataId))
                .findFirst()
                .orElseThrow(() -> new DataNotFoundException(String.format(
                        "EmailData с Id %s не найдено для этого пользователя", emailDataId)));
        emailData.setEmail(newEmail);
        userRepository.save(user);
    }

    @Override
    public void deleteUserEmail(Long userId, Long emailDataId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new DataNotFoundException(String.format("Пользователь c Id %s не найден", userId)));
        boolean removed = user.getEmailData()
                .removeIf(ed -> ed.getId().equals(emailDataId));
        if (!removed) {
            throw new DataNotFoundException(String.format(
                    "EmailData с Id %s не найдено для этого пользователя", emailDataId));
        }
        userRepository.save(user);
    }
}
