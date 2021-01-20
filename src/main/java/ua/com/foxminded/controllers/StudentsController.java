package ua.com.foxminded.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.com.foxminded.domain.entity.StudentEntity;
import ua.com.foxminded.domain.entity.mappers.StudentMapper;
import ua.com.foxminded.domain.entity.mappers.StudentMapperImpl;
import ua.com.foxminded.service.StudentService;

@Controller
@RequestMapping("/students")
public class StudentsController {

    private final StudentService studentService;
    private final StudentMapper studentMapper;


    public StudentsController(StudentService studentService, StudentMapper studentMapper, StudentMapperImpl studentMapper1) {
        this.studentService = studentService;
        this.studentMapper = studentMapper;
    }


    @GetMapping
    public String showStudents(Model model){
        model.addAttribute("students",studentService.readAll());

        return "students/index";
    }


    @GetMapping("/new")
    public String create(Model model){
        model.addAttribute("student" ,new StudentEntity());
        return "students/new_student";
    }

    @PostMapping
    public String createStudent(@ModelAttribute("student") StudentEntity studentEntity){
        studentService.save(studentEntity);
        return "redirect:/students";
    }

    @GetMapping("/{id}/edit")
    public String showEdit(@PathVariable("id")int id, Model model){
        model.addAttribute("student",studentService.findOne(id));
        return "students/edit_student";
    }


    @PatchMapping("{id}")
    public String editStudent(@ModelAttribute("student") StudentEntity studentEntity, @PathVariable("id") int id){
        studentEntity.setStudentId(id);
        studentService.update(studentEntity);
        return "redirect:/students";
    }

    @GetMapping("{id}/delete")
    public String deleteStudent(@PathVariable("id") int id){
        studentService.delete(id);
        return "redirect:/students";
    }

}
