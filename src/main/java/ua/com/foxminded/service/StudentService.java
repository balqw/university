package ua.com.foxminded.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.com.foxminded.domain.dao.LessonDao;
import ua.com.foxminded.domain.dao.StudentDao;
import ua.com.foxminded.domain.entity.LessonEntity;
import ua.com.foxminded.domain.entity.StudentEntity;
import ua.com.foxminded.domain.dto.StudentDTO;
import ua.com.foxminded.domain.dto.StudentSummaryInfo;
import ua.com.foxminded.domain.mappers.StudentMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentDao studentDao;
    private final LessonDao lessonDao;
    private final StudentMapper studentMapper;

    public StudentEntity save(StudentDTO dto) {
        StudentEntity entity = studentMapper.toStudentEntity(dto);
        return studentDao.save(entity);
    }

    public List<StudentDTO> readAll() {

        return studentMapper.toDtos(studentDao.readAll());
    }

    public StudentDTO findOne(Integer id) {
        return studentMapper.toStudentDTO(studentDao.findOne(id));
    }

    public StudentDTO update(StudentDTO studentDTO) {
        StudentEntity entity = studentMapper.toStudentEntity(studentDTO);
         studentDao.update(entity);
        return studentDTO;
    }

    public void delete(Integer id) {
        studentDao.delete(id);
    }

    public StudentSummaryInfo combineSummaryInfo(Integer id) {
        StudentEntity student = studentDao.findOne(id);
        List<LessonEntity> lessons = lessonDao.findByGroup(student.getGroup().getGroupId());
        return StudentSummaryInfo.builder()
                .id(student.getStudentId())
                .name(String.format("%s %s", student.getFirstName(), student.getLastName()))
                .lessons(lessons)
                .build();
    }
}
