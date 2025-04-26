package by.mikhalevich.grandcapitaltesttask.dao.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * Класс-сущность пользователя
 * @author Alex Mikhalevich
 * @created 2025-04-25 15:18
 */
@Entity
@Table(name = "\"USER\"")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

    /**
     * Id пользователя
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"ID\"")
    private Long id;

    /**
     * Имя пользователя
     */
    @Column(name = "\"NAME\"")
    private String name;

    /**
     * Дата рождения пользователя, формат dd.mm.yyyy
     */
    @Column(name = "\"DATE_OF_BIRTH\"")
    private LocalDate dateOfBirth;

    /**
     * Пароль пользователя, минимальная длина 8, максимальная 500
     */
    @Column(name = "\"PASSWORD\"")
    private String password;

    /**
     * Список адресов электронной почты пользователя
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<EmailData> emailData;

    /**
     * Список номеров телефонов пользователя
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<PhoneData> phoneData;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return Objects.equals(name, user.name) && Objects.equals(dateOfBirth, user.dateOfBirth)
                && Objects.equals(password, user.password) && Objects.equals(emailData, user.emailData)
                && Objects.equals(phoneData, user.phoneData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, dateOfBirth, password, emailData, phoneData);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", password='" + password + '\'' +
                ", emailData=" + emailData +
                ", phoneData=" + phoneData +
                '}';
    }
}
