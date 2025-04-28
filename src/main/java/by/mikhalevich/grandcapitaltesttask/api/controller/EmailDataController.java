package by.mikhalevich.grandcapitaltesttask.api.controller;

import by.mikhalevich.grandcapitaltesttask.service.dto.EmailRequestDto;
import by.mikhalevich.grandcapitaltesttask.service.intrfc.EmailDataService;
import by.mikhalevich.grandcapitaltesttask.service.security.jwt.JwtUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
 * Класс-контроллер для работы с адресами электронной почты пользователя
 * @author Alex Mikhalevich
 * @created 2025-04-26 13:57
 */
@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Slf4j
@Validated
@Tag(name = "API электронной почты пользователя", description = "Контроллер для работы с адресами электронной почты пользователя")
public class EmailDataController {

    /**
     * Сервис адресов электронной почты
     */
    private final EmailDataService emailDataService;

    /**
     * Добавление адреса электронной почты пользователю
     * @param userId Id пользователя
     * @param emailDto адрес электронной почты
     * @param currentUser текущий пользователь
     * @return результат
     */
    @PostMapping("/{userId}/emails")
    @Operation(summary = "Добавление email", description = "Добавление email в список адресов электронной почты пользователя")
    public ResponseEntity<Object> addEmail(@PathVariable Long userId,
                                      @RequestBody @Valid EmailRequestDto emailDto,
                                      @AuthenticationPrincipal JwtUser currentUser) {
        if (!currentUser.getUserId().equals(userId)) {
            log.info(LOG_MESSAGE_403, currentUser.getUserId());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(MESSAGE_403);
        }
        emailDataService.addEmailForUser(userId, emailDto.email());
        return ResponseEntity.ok().build();
    }

    /**
     * Обновление адреса электронной почты пользователю
     * @param userId Id пользователя
     * @param emailDataId Id адреса электронной почты
     * @param emailDto адрес электронной почты
     * @param currentUser текущий пользователь
     * @return результат
     */
    @PutMapping("/{userId}/emails/{emailDataId}")
    @Operation(summary = "Обновление email", description = "Обновление email в списке адресов электронной почты пользователя")
    public ResponseEntity<Object> updateEmail(@PathVariable Long userId,
                                         @PathVariable Long emailDataId,
                                         @RequestBody @Valid EmailRequestDto emailDto,
                                         @AuthenticationPrincipal JwtUser currentUser) {
        if (!currentUser.getUserId().equals(userId)) {
            log.info(LOG_MESSAGE_403, currentUser.getUserId());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(MESSAGE_403);
        }
        emailDataService.updateUserEmail(userId, emailDataId, emailDto.email());
        return ResponseEntity.ok().build();
    }

    /**
     * Удаление адреса электронной почты пользователя
     * @param userId Id пользователя
     * @param emailDataId Id адреса электронной почты
     * @param currentUser текущий пользователь
     * @return результат
     */
    @DeleteMapping("/{userId}/emails/{emailDataId}")
    @Operation(summary = "Удаление email", description = "Удаление email из списка адресов электронной почты пользователя")
    public ResponseEntity<Object> deleteEmail(@PathVariable Long userId,
                                         @PathVariable Long emailDataId,
                                         @AuthenticationPrincipal JwtUser currentUser) {
        if (!currentUser.getUserId().equals(userId)) {
            log.info(LOG_MESSAGE_403, currentUser.getUserId());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(MESSAGE_403);
        }
        emailDataService.deleteUserEmail(userId, emailDataId);
        return ResponseEntity.ok().build();
    }

}
