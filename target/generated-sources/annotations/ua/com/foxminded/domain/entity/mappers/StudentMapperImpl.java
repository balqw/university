package ua.com.foxminded.domain.entity.mappers;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;
import ua.com.foxminded.domain.entity.StudentEntity;
import ua.com.foxminded.domain.entity.dto.StudentDTO;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-02-17T15:22:18+0200",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_282 (Amazon.com Inc.)"
)
@Component
public class StudentMapperImpl implements StudentMapper {

    @Override
    public StudentDTO toStudentDTO(StudentEntity entity) {
        if ( entity == null ) {
            return null;
        }

        StudentDTO studentDTO = new StudentDTO();

        studentDTO.setId( entity.getStudentId() );
        studentDTO.setName( entity.getFirstName() );
        studentDTO.setSurName( entity.getLastName() );
        studentDTO.setCourse( entity.getCourse() );

        return studentDTO;
    }

    @Override
    public List<StudentDTO> toDtos(List<StudentEntity> entity) {
        if ( entity == null ) {
            return null;
        }

        List<StudentDTO> list = new ArrayList<StudentDTO>( entity.size() );
        for ( StudentEntity studentEntity : entity ) {
            list.add( toStudentDTO( studentEntity ) );
        }

        return list;
    }

    @Override
    public List<StudentEntity> toEntities(List<StudentDTO> entity) {
        if ( entity == null ) {
            return null;
        }

        List<StudentEntity> list = new ArrayList<StudentEntity>( entity.size() );
        for ( StudentDTO studentDTO : entity ) {
            list.add( toStudentEntity( studentDTO ) );
        }

        return list;
    }

    @Override
    public StudentEntity toStudentEntity(StudentDTO studentDTO) {
        if ( studentDTO == null ) {
            return null;
        }

        StudentEntity studentEntity = new StudentEntity();

        studentEntity.setStudentId( studentDTO.getId() );
        studentEntity.setFirstName( studentDTO.getName() );
        studentEntity.setLastName( studentDTO.getSurName() );
        studentEntity.setCourse( studentDTO.getCourse() );

        return studentEntity;
    }
}
