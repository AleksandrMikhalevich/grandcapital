package by.mikhalevich.grandcapitaltesttask.service.intrfc;

/**
 * Интерфейс-сервис для работы с адресами электронной почты пользователя
 * @author Alex Mikhalevich
 * @created 2025-04-26 13:22
 */
public interface EmailDataService {

    /**
     * Добавляет новый адрес электронной почты для пользователя, если он не занят
     * @param userId Id пользователя
     * @param email адрес новой электронной почты
     */
    void addEmailForUser(Long userId, String email);

    /**
     * Обновляет адрес электронной почты пользователя
     * @param userId Id пользователя
     * @param emailDataId Id адреса электронной почты для обновления
     * @param newEmail обновленный адрес электронной почты
     */
    void updateUserEmail(Long userId, Long emailDataId, String newEmail);

    /**
     * Удаляет адрес электронной почты пользователя
     * @param userId Id пользователя
     * @param emailDataId Id адреса электронной почты для удаления
     */
    void deleteUserEmail(Long userId, Long emailDataId);
}
