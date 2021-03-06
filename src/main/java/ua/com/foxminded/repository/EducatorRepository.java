package ua.com.foxminded.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.foxminded.domain.entity.EducatorEntity;

public interface EducatorRepository extends JpaRepository<EducatorEntity, Integer> {
    Boolean existsEducatorEntityByFirstNameAndAndFirstName(String name, String lastName);
}
