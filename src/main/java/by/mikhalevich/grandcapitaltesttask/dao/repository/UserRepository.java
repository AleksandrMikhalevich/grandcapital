package by.mikhalevich.grandcapitaltesttask.dao.repository;

import by.mikhalevich.grandcapitaltesttask.dao.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * JPA-репозиторий для {@link User}
 * @author Alex Mikhalevich
 * @created 2025-04-26 13:32
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Метод нахождения пользователя по адресу электронной почты
     * @param email адрес электронной почты
     * @return результат
     */
    @Query("SELECT u FROM User u JOIN u.emailData ed WHERE ed.email = :email")
    User findUserByEmail(@Param("email") String email);

}