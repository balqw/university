package ua.com.foxminded.domain.entity.mappers;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ua.com.foxminded.domain.entity.Group;
import ua.com.foxminded.domain.entity.StudentEntity;
import ua.com.foxminded.domain.entity.dto.StudentDTO;

import java.util.List;

@Mapper(componentModel = "spring", uses = {Group.class})

public interface StudentMapper {

    @Mapping(source = "studentId", target = "id")
    @Mapping(source = "firstName", target = "name")
    @Mapping(source = "lastName", target = "surName")
    StudentDTO toStudentDTO(StudentEntity entity);

    List<StudentDTO> toDtos(List<StudentEntity> entity);

    List<StudentEntity> toEntities(List<StudentDTO> entity);

    @InheritInverseConfiguration
    StudentEntity toStudentEntity(StudentDTO studentDTO);


}
