package by.mikhalevich.grandcapitaltesttask.api.controller;

import by.mikhalevich.grandcapitaltesttask.service.dto.UserSearchResponseDto;
import by.mikhalevich.grandcapitaltesttask.service.intrfc.UserService;
import by.mikhalevich.grandcapitaltesttask.service.specification.UserSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

/**
 * Класс-контроллер для работы с пользователями
 * @author Alex Mikhalevich
 * @created 2025-04-27 13:54
 */
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Validated
public class UserController {

    /**
     * Сервис пользователей
     */
    private final UserService userService;

    /**
     * Поиск пользователей по параметрам с пагинацией
     * @param dateOfBirth дата рождения пользователя
     * @param phone номер телефона пользователя
     * @param name имя пользователя
     * @param email адрес электронной почты пользователя
     * @param page страница
     * @param size размер страницы
     * @return результат
     */
    @GetMapping("/search")
    public ResponseEntity<Page<UserSearchResponseDto>> search(@RequestParam(value = "dateOfBirth", required = false)
                                                              @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate dateOfBirth,
                                                              @RequestParam(value = "phone", required = false) String phone,
                                                              @RequestParam(value = "name", required = false) String name,
                                                              @RequestParam(value = "email", required = false) String email,
                                                              @RequestParam(value = "page", defaultValue = "0") int page,
                                                              @RequestParam(value = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<UserSearchResponseDto> result = userService.searchUsers(
                UserSpecification.withFilters(dateOfBirth, phone, name, email),
                pageable);
        return ResponseEntity.ok(result);
    }
}
