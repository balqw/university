package ua.com.foxminded.domain.entity.mappers;

import org.mapstruct.Mapper;
import ua.com.foxminded.domain.entity.StudentGroupEntity;
import ua.com.foxminded.domain.entity.dto.StudentGroupDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentGroupMapper {
    StudentGroupDTO toDto (StudentGroupEntity entity);
    StudentGroupEntity toEntity (StudentGroupDTO studentGroupDTO);

    List<StudentGroupDTO> toDtos (List<StudentGroupEntity>entities);
    List<StudentGroupDTO> toEntities(List<StudentGroupDTO>dtos);
}
