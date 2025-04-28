package by.mikhalevich.grandcapitaltesttask.dao.repository;

import by.mikhalevich.grandcapitaltesttask.dao.model.PhoneData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * JPA-репозиторий для {@link PhoneData}
 * @author Alex Mikhalevich
 * @created 2025-04-26 13:19
 */
public interface PhoneDataRepository extends JpaRepository<PhoneData, Long> {

    /**
     * Нахождение телефона пользователя
     * @param phone номер телефона
     * @return результат
     */
    Optional<PhoneData> findByPhone(String phone);

}
