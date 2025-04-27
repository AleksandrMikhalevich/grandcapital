package by.mikhalevich.grandcapitaltesttask.service.intrfc;

/**
 * Интерфейс-сервис для работы с номерами телефона пользователя
 * @author Alex Mikhalevich
 * @created 2025-04-27 12:59
 */
public interface PhoneDataService {

    /**
     * Добавляет новый номер телефона для пользователя, если он не занят
     * @param userId Id пользователя
     * @param phone номер телефона
     */
    void addPhoneForUser(Long userId, String phone);

    /**
     * Обновляет номер телефона пользователя
     * @param userId Id пользователя
     * @param phoneDataId Id номера телефона для обновления
     * @param newPhone обновленный номер телефона
     */
    void updateUserPhone(Long userId, Long phoneDataId, String newPhone);

    /**
     * Удаляет номер телефона пользователя
     * @param userId Id пользователя
     * @param phoneDataId Id номера телефона для удаления
     */
    void deleteUserPhone(Long userId, Long phoneDataId);
}
