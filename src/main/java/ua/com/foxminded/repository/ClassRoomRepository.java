package ua.com.foxminded.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.foxminded.domain.entity.ClassRoomEntity;

public interface ClassRoomRepository extends JpaRepository<ClassRoomEntity,Integer> {
    Boolean existsClassRoomEntitiesByNumber(Integer number);
    ClassRoomEntity findClassRoomEntityByNumber(Integer number);
}
