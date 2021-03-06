package ua.com.foxminded.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

import org.springframework.transaction.annotation.Transactional;
import ua.com.foxminded.domain.entity.StudentEntity;
import ua.com.foxminded.domain.dto.StudentDTO;
import ua.com.foxminded.domain.dto.StudentSummaryInfo;
import ua.com.foxminded.domain.exceptions.NoDataFoundException;
import ua.com.foxminded.domain.exceptions.NotFoundException;
import ua.com.foxminded.domain.mappers.StudentMapper;
import ua.com.foxminded.repository.StudentRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentMapper studentMapper;
    private final StudentRepository repository;


    @Transactional
    public StudentDTO save(StudentDTO dto) {
        StudentEntity entity = studentMapper.toStudentEntity(dto);
        if (repository.existsByFirstNameAndLastName(entity.getFirstName(), entity.getLastName()))
            throw new IllegalArgumentException("student already exist");
        repository.save(entity);
        return dto;
    }

    @Transactional
    public List<StudentDTO> findAll(){
        return repository.findAll().stream().map(studentMapper::toStudentDTO).collect(Collectors.toList());
    }

    @Transactional
    public Page<StudentDTO> findAllPage(Pageable pageable){
        return repository.findAll(pageable).map(studentMapper::toStudentDTO);
    }

    @Transactional
    public StudentDTO findOne(Integer id) {
        return studentMapper.toStudentDTO(repository.findById(id)
            .orElseThrow(()->new NotFoundException(format("No student found with id %d", id))));
    }

    @Transactional
    public StudentDTO update(StudentDTO studentDTO) {
        repository.existsById(studentDTO.getId());
        StudentEntity entity = studentMapper.toStudentEntity(studentDTO);
        repository.save(entity);
        return studentDTO;
    }

    @Transactional
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Transactional
    public StudentSummaryInfo combineSummaryInfo(Integer id) {
        Optional<StudentEntity> option = repository.findById(id);
        if (option.isPresent()) {
            StudentEntity student = option.get();
            return StudentSummaryInfo.builder()
                    .id(student.getStudentId())
                    .name(String.format("%s %s", student.getFirstName(), student.getLastName()))
                    .build();
        } else
            throw new NotFoundException(format("No student found with id %d", id));
    }
}
