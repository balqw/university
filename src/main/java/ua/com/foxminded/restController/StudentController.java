package ua.com.foxminded.restController;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.com.foxminded.domain.dto.StudentDTO;
import ua.com.foxminded.domain.entity.StudentEntity;
import ua.com.foxminded.domain.exceptions.NotFoundException;
import ua.com.foxminded.domain.mappers.StudentMapper;
import ua.com.foxminded.repository.StudentRepository;
import ua.com.foxminded.service.StudentService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static java.lang.String.format;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;
    private final StudentMapper studentMapper;

    @GetMapping("/students")
    List<StudentDTO> findAll(){
        return studentService.readAll();
    }

    @GetMapping("students/{id}")
    StudentDTO findById(@PathVariable("id") Integer id){
        return studentService.findOne(id);
    }


    @PostMapping("students")
    StudentDTO save(@RequestBody @Valid StudentDTO studentDTO){
        return studentService.save(studentDTO);
    }

    @PutMapping("students/{id}")
    StudentDTO update(@PathVariable("id") Integer id,@RequestBody @Valid StudentDTO studentDTO){
        studentDTO.setId(id);
        return studentService.update(studentDTO);
    }

    @DeleteMapping("students/{id}")
    void delete(@PathVariable("id") Integer id){
        studentService.delete(id);
    }
}
