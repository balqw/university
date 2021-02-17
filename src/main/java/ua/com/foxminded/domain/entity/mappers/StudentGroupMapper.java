package ua.com.foxminded.domain.entity.mappers;

import org.mapstruct.Mapper;
import ua.com.foxminded.domain.entity.Group;
import ua.com.foxminded.domain.entity.dto.StudentGroupDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentGroupMapper {
    StudentGroupDTO toDto (Group entity);
    Group toEntity (StudentGroupDTO studentGroupDTO);

    List<StudentGroupDTO> toDtos (List<Group>entities);
    List<StudentGroupDTO> toEntities(List<StudentGroupDTO>dtos);
}
