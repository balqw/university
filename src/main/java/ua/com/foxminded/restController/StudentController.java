package ua.com.foxminded.restController;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;
import ua.com.foxminded.domain.dto.StudentDTO;
import ua.com.foxminded.service.StudentService;

import javax.validation.Valid;




@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/students")
    Page<StudentDTO> findAll(@PageableDefault(size=5, sort = "lastName") Pageable pageable){
        return studentService.findAllPage(pageable);
    }

    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("students/{id}")
    StudentDTO findById(@PathVariable("id") Integer id){
        return studentService.findOne(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "students")
    StudentDTO save(@RequestBody @Valid StudentDTO studentDTO){
        return studentService.save(studentDTO);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("students/{id}")
    StudentDTO update(@PathVariable("id") Integer id, @RequestBody @Valid StudentDTO studentDTO){
        studentDTO.setId(id);
        return studentService.update(studentDTO);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("students/{id}")
    void delete(@PathVariable("id") Integer id){
        studentService.delete(id);
    }
}
