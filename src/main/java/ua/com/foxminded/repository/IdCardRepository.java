package ua.com.foxminded.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.foxminded.domain.entity.IdCardEntity;

public interface IdCardRepository extends JpaRepository<IdCardEntity, Integer> {

}
