package ua.com.foxminded.domain.entity.mappers;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ua.com.foxminded.domain.entity.StudentEntity;
import ua.com.foxminded.domain.entity.dto.StudentDTO;


@Mapper(componentModel = "spring")
public interface StudentMapper {

    @Mapping(source = "firstName", target = "name")
    @Mapping(source = "lastName", target = "surName")
    StudentDTO toStudentDTO (StudentEntity entity);

    @InheritInverseConfiguration
    StudentEntity toStudentEntity(StudentDTO studentDTO);


}
