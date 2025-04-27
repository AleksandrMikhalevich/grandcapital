package by.mikhalevich.grandcapitaltesttask.service.specification;

import by.mikhalevich.grandcapitaltesttask.dao.model.EmailData;
import by.mikhalevich.grandcapitaltesttask.dao.model.PhoneData;
import by.mikhalevich.grandcapitaltesttask.dao.model.User;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс-спецификация для фильтрации и поиска пользователей по параметрам
 * @author Alex Mikhalevich
 * @created 2025-04-27 13:46
 */
public class UserSpecification {

    private UserSpecification() {

    }

    /**
     * Фильтрация и поиск пользователей по параметрам
     * @param dateOfBirth дата рождения пользователя
     * @param phone номер телефона пользователя
     * @param name имя пользователя
     * @param email адрес электронной почты пользователя
     * @return спецификация
     */
    public static Specification<User> withFilters(LocalDate dateOfBirth, String phone, String name, String email) {
        return (root, query, cb) -> {
            query.distinct(true);
            List<Predicate> predicates = new ArrayList<>();
            // Если передана «dateOfBirth», то фильтр записей, где «date_of_birth» больше чем переданный в запросе
            if (dateOfBirth != null) {
                predicates.add(cb.greaterThan(root.get("dateOfBirth"), dateOfBirth));
            }
            // Если передан «name», то фильтр по like форматом ‘{text-from-request-param}%
            if (name != null && !name.isEmpty()) {
                predicates.add(cb.like(root.get("name"), name + "%"));
            }
            // Если передан «phone», то фильтр по 100% сходству
            if (phone != null && !phone.isEmpty()) {
                Join<User, PhoneData> phoneJoin = root.join("phoneData", JoinType.LEFT);
                predicates.add(cb.equal(phoneJoin.get("phone"), phone));
            }
            // Если передан «email», то фильтр по 100% сходству
            if (email != null && !email.isEmpty()) {
                Join<User, EmailData> emailJoin = root.join("emailData", JoinType.LEFT);
                predicates.add(cb.equal(emailJoin.get("email"), email));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
