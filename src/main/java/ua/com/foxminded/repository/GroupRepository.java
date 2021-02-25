package ua.com.foxminded.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.foxminded.domain.entity.Group;

public interface GroupRepository extends JpaRepository<Group,Integer> {
    Boolean existsGroupByAbbreviate(String abbr);
}
