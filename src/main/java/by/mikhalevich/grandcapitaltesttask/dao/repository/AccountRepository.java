package by.mikhalevich.grandcapitaltesttask.dao.repository;

import by.mikhalevich.grandcapitaltesttask.dao.model.Account;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * JPA-репозиторий для {@link Account}
 * @author Alex Mikhalevich
 * @created 2025-04-27 18:15
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    /**
     * Нахождение счета пользователя для обновления
     * @param userId Id пользователя
     * @return результат
     */
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT a FROM Account a WHERE a.user.id = :userId")
    Optional<Account> findByUserIdForUpdate(@Param("userId") Long userId);
}