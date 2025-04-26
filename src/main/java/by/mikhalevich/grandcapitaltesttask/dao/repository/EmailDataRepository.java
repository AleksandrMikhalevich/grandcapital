package by.mikhalevich.grandcapitaltesttask.dao.repository;

import by.mikhalevich.grandcapitaltesttask.dao.model.EmailData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * JPA-репозиторий для {@link EmailData}
 * @author Alex Mikhalevich
 * @created 2025-04-26 13:15
 */
@Repository
public interface EmailDataRepository extends JpaRepository<EmailData, Long> {

    /**
     * Метод нахождения адреса электронной почты
     * @param email адрес электронной почты
     * @return результат
     */
    Optional<EmailData> findByEmail(String email);
}
