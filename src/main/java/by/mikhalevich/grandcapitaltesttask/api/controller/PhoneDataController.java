package by.mikhalevich.grandcapitaltesttask.api.controller;

import by.mikhalevich.grandcapitaltesttask.service.dto.PhoneRequestDto;
import by.mikhalevich.grandcapitaltesttask.service.intrfc.PhoneDataService;
import by.mikhalevich.grandcapitaltesttask.service.security.jwt.JwtUser;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static by.mikhalevich.grandcapitaltesttask.service.util.Constants.LOG_MESSAGE_403;
import static by.mikhalevich.grandcapitaltesttask.service.util.Constants.MESSAGE_403;

/**
 * Класс-контроллер для работы с номерами телефона пользователя
 * @author Alex Mikhalevich
 * @created 2025-04-27 13:21
 */
@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Slf4j
@Validated
public class PhoneDataController {

    /**
     * Сервис телефонов пользователей
     */
    private final PhoneDataService phoneDataService;

    /**
     * Добавление номера телефона пользователю
     * @param userId Id пользователя
     * @param phoneDto номера телефона
     * @param currentUser текущий пользователь
     * @return результат
     */
    @PostMapping("/{userId}/phones")
    public ResponseEntity<Object> addPhone(@PathVariable Long userId,
                                      @RequestBody @Valid PhoneRequestDto phoneDto,
                                      @AuthenticationPrincipal JwtUser currentUser) {
        if (!currentUser.getUserId().equals(userId)) {
            log.info(LOG_MESSAGE_403, currentUser.getUserId());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(MESSAGE_403);
        }
        phoneDataService.addPhoneForUser(userId, phoneDto.phone());
        return ResponseEntity.ok().build();
    }

    /**
     * Обновление номера телефона пользователю
     * @param userId Id пользователя
     * @param phoneDataId Id номера телефона
     * @param phoneDto номера телефона
     * @param currentUser текущий пользователь
     * @return результат
     */
    @PutMapping("/{userId}/phones/{phoneDataId}")
    public ResponseEntity<Object> updatePhone(@PathVariable Long userId,
                                         @PathVariable Long phoneDataId,
                                         @RequestBody @Valid PhoneRequestDto phoneDto,
                                         @AuthenticationPrincipal JwtUser currentUser) {
        if (!currentUser.getUserId().equals(userId)) {
            log.info(LOG_MESSAGE_403, currentUser.getUserId());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(MESSAGE_403);
        }
        phoneDataService.updateUserPhone(userId, phoneDataId, phoneDto.phone());
        return ResponseEntity.ok().build();
    }

    /**
     * Удаление номера телефона пользователя
     * @param userId Id пользователя
     * @param phoneDataId Id номера телефона
     * @param currentUser текущий пользователь
     * @return результат
     */
    @DeleteMapping("/{userId}/phones/{phoneDataId}")
    public ResponseEntity<Object> deletePhone(@PathVariable Long userId,
                                         @PathVariable Long phoneDataId,
                                         @AuthenticationPrincipal JwtUser currentUser) {
        if (!currentUser.getUserId().equals(userId)) {
            log.info(LOG_MESSAGE_403, currentUser.getUserId());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(MESSAGE_403);
        }
        phoneDataService.deleteUserPhone(userId, phoneDataId);
        return ResponseEntity.ok().build();
    }

}
