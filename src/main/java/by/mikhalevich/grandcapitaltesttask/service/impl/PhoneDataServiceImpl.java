package by.mikhalevich.grandcapitaltesttask.service.impl;

import by.mikhalevich.grandcapitaltesttask.api.exception.DataNotFoundException;
import by.mikhalevich.grandcapitaltesttask.api.exception.NotUniqueDataException;
import by.mikhalevich.grandcapitaltesttask.dao.model.PhoneData;
import by.mikhalevich.grandcapitaltesttask.dao.model.User;
import by.mikhalevich.grandcapitaltesttask.dao.repository.PhoneDataRepository;
import by.mikhalevich.grandcapitaltesttask.dao.repository.UserRepository;
import by.mikhalevich.grandcapitaltesttask.service.intrfc.PhoneDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static by.mikhalevich.grandcapitaltesttask.service.util.Constants.*;

/**
 * Класс-сервис для работы с номерами телефона пользователя
 * @author Alex Mikhalevich
 * @created 2025-04-27 13:06
 */
@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class PhoneDataServiceImpl implements PhoneDataService {

    /**
     * Репозиторий пользователя
     */
    private final UserRepository userRepository;

    /**
     * Репозиторий номеров телефона
     */
    private final PhoneDataRepository phoneDataRepository;

    @Override
    public void addPhoneForUser(Long userId, String phone) {
        if (phoneDataRepository.findByPhone(phone).isPresent()) {
            log.info(LOG_NOT_UNIQUE_PHONE_ERROR_MESSAGE, phone);
            throw new NotUniqueDataException(String.format(NOT_UNIQUE_PHONE_ERROR_MESSAGE, phone));
        }
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new DataNotFoundException(String.format(USER_ID_NOT_FOUND_MESSAGE, userId)));
        PhoneData phoneData = PhoneData.builder()
                .phone(phone)
                .user(user)
                .build();
        user.getPhoneData().add(phoneData);
        userRepository.save(user);
    }

    @Override
    public void updateUserPhone(Long userId, Long phoneDataId, String newPhone) {
        if (phoneDataRepository.findByPhone(newPhone).isPresent()) {
            log.info(LOG_NOT_UNIQUE_PHONE_ERROR_MESSAGE, newPhone);
            throw new NotUniqueDataException(String.format(NOT_UNIQUE_PHONE_ERROR_MESSAGE, newPhone));
        }
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new DataNotFoundException(String.format(USER_ID_NOT_FOUND_MESSAGE, userId)));
        PhoneData phoneData = user.getPhoneData().stream()
                .filter(ed -> ed.getId().equals(phoneDataId))
                .findFirst()
                .orElseThrow(() -> new DataNotFoundException(String.format(PHONE_NOT_FOUND_MESSAGE, userId)));
        phoneData.setPhone(newPhone);
        userRepository.save(user);
    }

    @Override
    public void deleteUserPhone(Long userId, Long phoneDataId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new DataNotFoundException(String.format(USER_ID_NOT_FOUND_MESSAGE, userId)));
        boolean removed = user.getPhoneData()
                .removeIf(ed -> ed.getId().equals(phoneDataId));
        if (!removed) {
            log.info(LOG_PHONE_ID_NOT_FOUND_MESSAGE, phoneDataId, userId);
            throw new DataNotFoundException(String.format(PHONE_NOT_FOUND_MESSAGE, userId));
        }
        userRepository.save(user);
    }
}
