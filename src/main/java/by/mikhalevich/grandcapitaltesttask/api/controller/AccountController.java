package by.mikhalevich.grandcapitaltesttask.api.controller;

import by.mikhalevich.grandcapitaltesttask.service.intrfc.AccountService;
import by.mikhalevich.grandcapitaltesttask.service.security.jwt.JwtUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

import static by.mikhalevich.grandcapitaltesttask.service.util.Constants.*;

/**
 * Класс-контроллер для работы со счетами пользователей
 * @author Alex Mikhalevich
 * @created 2025-04-27 19:12
 */
@RestController
@RequestMapping("/api/v1/account")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "API счетов", description = "Контроллер для работы со счетами пользователей")
public class AccountController {

    /**
     * Сервис счетов
     */
    private final AccountService accountService;

    /**
     * Перевод средств между пользователями
     * @param toUserId пользователь-получатель
     * @param amount сумма перевода
     * @param currentUser пользователь отправитель
     * @return результат
     */
    @PostMapping("/transfer")
    @Operation(summary = "Перевод средств", description = "Перевод средств от одного пользователя к другому")
    public ResponseEntity<String> transfer(@RequestParam("toUserId") Long toUserId,
                                           @RequestParam("amount") BigDecimal amount,
                                           @AuthenticationPrincipal JwtUser currentUser) {
        Long fromUserId = currentUser.getUserId();
        if (fromUserId.equals(toUserId)) {
            log.info(LOG_MONEY_TRANSFER_ERROR_MESSAGE, currentUser.getUserId());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(MONEY_TRANSFER_ERROR_MESSAGE);
        }
        accountService.transferMoney(fromUserId, toUserId, amount);
        return ResponseEntity.ok(MONEY_TRANSFER_SUCCESS_MESSAGE);
    }
}
