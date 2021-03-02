package ua.com.foxminded.domain.mappers;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ua.com.foxminded.domain.dto.ClassRoomDTO;
import ua.com.foxminded.domain.entity.ClassRoomEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClassroomMapper {
    @Mapping(target = "id", source = "classId")
    ClassRoomDTO toDto(ClassRoomEntity entity);

    @InheritInverseConfiguration
    ClassRoomEntity toEntity(ClassRoomDTO dto);

    List<ClassRoomDTO> toDtos(List<ClassRoomEntity>entities);
    List<ClassRoomEntity> toEntities(List<ClassRoomDTO>dtos);
}
