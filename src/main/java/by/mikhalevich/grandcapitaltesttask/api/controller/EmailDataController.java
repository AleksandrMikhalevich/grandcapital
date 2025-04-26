package by.mikhalevich.grandcapitaltesttask.api.controller;

import by.mikhalevich.grandcapitaltesttask.service.dto.EmailRequestDto;
import by.mikhalevich.grandcapitaltesttask.service.intrfc.EmailDataService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Класс-контроллер для работы с адресами электронной почты пользователя
 * @author Alex Mikhalevich
 * @created 2025-04-26 13:57
 */
@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Slf4j
@Validated
public class EmailDataController {

    private final EmailDataService emailDataService;

    /**
     * Добавление адреса электронной почты пользователю
     * @param userId Id пользователя
     * @param emailDto адрес электронной почты
     //* @param currentUser текущий пользователь
     * @return статус
     */
    @PostMapping("/{userId}/emails")
    public ResponseEntity<?> addEmail(@PathVariable Long userId,
                                      @RequestBody @Valid EmailRequestDto emailDto) {
        // Проверяем, что текущий пользователь соответствует userId
//        if (!currentUser.getId().equals(userId)) {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Доступ запрещен");
//        }
        emailDataService.addEmailForUser(userId, emailDto.email());
        return ResponseEntity.ok().build();
    }

    /**
     * Обновление адреса электронной почты пользователю
     * @param userId Id пользователя
     * @param emailDataId Id адреса электронной почты
     * @param emailDto адрес электронной почты
     //* @param currentUser текущий пользователь
     * @return статус
     */
    @PutMapping("/{userId}/emails/{emailDataId}")
    public ResponseEntity<?> updateEmail(@PathVariable Long userId,
                                         @PathVariable Long emailDataId,
                                         @RequestBody @Valid EmailRequestDto emailDto) {
//        if (!currentUser.getId().equals(userId)) {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Доступ запрещен");
//        }
        emailDataService.updateUserEmail(userId, emailDataId, emailDto.email());
        return ResponseEntity.ok().build();
    }

    /**
     * Удаление адреса электронной почты
     * @param userId Id пользователя
     * @param emailDataId Id адреса электронной почты
     //* @param currentUser текущий пользователь
     * @return статус
     */
    @DeleteMapping("/{userId}/emails/{emailDataId}")
    public ResponseEntity<?> deleteEmail(@PathVariable Long userId,
                                         @PathVariable Long emailDataId) {
//        if (!currentUser.getId().equals(userId)) {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Доступ запрещен");
//        }
        emailDataService.deleteUserEmail(userId, emailDataId);
        return ResponseEntity.ok().build();
    }

}
