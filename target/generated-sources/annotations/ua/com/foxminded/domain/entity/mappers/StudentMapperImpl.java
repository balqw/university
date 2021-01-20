package ua.com.foxminded.domain.entity.mappers;

import javax.annotation.Generated;
import org.springframework.stereotype.Component;
import ua.com.foxminded.domain.entity.StudentEntity;
import ua.com.foxminded.domain.entity.dto.StudentDTO;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-01-14T00:05:46+0200",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_261 (Oracle Corporation)"
)
@Component
public class StudentMapperImpl implements StudentMapper {

    @Override
    public StudentEntity toStudentDto(StudentEntity entity) {
        if ( entity == null ) {
            return null;
        }

        StudentEntity studentEntity = new StudentEntity();

        return studentEntity;
    }

    @Override
    public StudentDTO fromStudentEntity(StudentDTO dto) {
        if ( dto == null ) {
            return null;
        }

        StudentDTO studentDTO = new StudentDTO();

        return studentDTO;
    }
}
