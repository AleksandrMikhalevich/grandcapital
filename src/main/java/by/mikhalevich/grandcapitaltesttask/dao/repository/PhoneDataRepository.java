package by.mikhalevich.grandcapitaltesttask.dao.repository;

import by.mikhalevich.grandcapitaltesttask.dao.model.PhoneData;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPA-репозиторий для {@link PhoneData}
 * @author Alex Mikhalevich
 * @created 2025-04-26 13:19
 */
public interface PhoneDataRepository extends JpaRepository<PhoneData, Long> {

}
