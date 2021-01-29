package ua.com.foxminded.domain.entity.mappers;

import org.junit.jupiter.api.Test;
import ua.com.foxminded.domain.entity.StudentEntity;
import ua.com.foxminded.domain.entity.dto.StudentDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class StudentMapperTest {
    private static final StudentMapperImpl studentMapper = new StudentMapperImpl();

    @Test
    void shouldReturnDtoWithTheSameFields() {
        StudentEntity entity = new StudentEntity();
        entity.setStudentId(123456789);
        entity.setFirstName("FirstName");
        entity.setLastName("LastName");

        StudentDTO result = studentMapper.toStudentDTO(entity);

        assertEquals(entity.getStudentId(), result.getId());
        assertEquals(entity.getFirstName(), result.getName());
        assertEquals(entity.getLastName(), result.getSurName());
    }

    @Test
    void shouldReturnEmptyDtoWhenEntityIsEmpty() {
        StudentEntity entity = new StudentEntity();

        StudentDTO result = studentMapper.toStudentDTO(entity);

        assertEquals(entity.getStudentId(), result.getId());
        assertEquals(entity.getFirstName(), result.getName());
        assertEquals(entity.getLastName(), result.getSurName());
    }

    @Test
    void shouldReturnNullWhenArgumentIsNull() {
        StudentDTO studentDTO = studentMapper.toStudentDTO(null);

        assertNull(studentDTO);
    }


    @Test
    void shouldReturnEntityWithTheSameFields() {
        StudentEntity expectedResult = new StudentEntity();
        expectedResult.setStudentId(123456789);
        expectedResult.setFirstName("FirstName");
        expectedResult.setLastName("LastName");
        StudentDTO dto = new StudentDTO();
        dto.setId(123456789);
        dto.setName("FirstName");
        dto.setSurName("LastName");

        StudentEntity result = studentMapper.toStudentEntity(dto);

        assertEquals(expectedResult, result);
    }
}