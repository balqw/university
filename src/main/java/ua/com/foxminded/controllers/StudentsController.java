package ua.com.foxminded.controllers;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.com.foxminded.domain.dto.StudentDTO;
import ua.com.foxminded.service.GroupService;
import ua.com.foxminded.service.StudentService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/students")
@AllArgsConstructor
public class StudentsController {

    private final StudentService studentService;
    private final GroupService groupService;


    @GetMapping("/{page}")
    public String findAll(Model model,@PathVariable("page") int pageNo) {
        int pageSize = 5;
        Pageable pageable = PageRequest.of(pageNo-1, pageSize, Sort.by("lastName"));
        Page<StudentDTO>page = studentService.findAll(pageable);
        List<StudentDTO>students = page.getContent();
        model.addAttribute("students",students);
        model.addAttribute("currentPage",pageNo);
        return "students/index";
    }

    @GetMapping("/{id}/details")
    public String getDetails(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("student",studentService.combineSummaryInfo(id));
        return "students/details";
    }


    @GetMapping("/new")
    public String create(Model model) {
        model.addAttribute("student", new StudentDTO());
        model.addAttribute("groups",groupService.findAll());
        return "students/new_student";
    }

    @PostMapping
    public String createStudent(Model model,@ModelAttribute("student") @Valid StudentDTO dto, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            model.addAttribute("student", dto);
            model.addAttribute("groups",groupService.findAll());
            return "students/new_student";
        }
        studentService.save(dto);
        return "redirect:/students/1";
    }

    @GetMapping("/{id}/edit")
    public String showEdit(@PathVariable("id") int id, Model model) {
        model.addAttribute("student", studentService.findOne(id));
        model.addAttribute("groups",groupService.findAll());
        return "students/edit_student";
    }

    @PostMapping("{id}/edit")
    public String editStudent( Model model, @ModelAttribute("student") @Valid StudentDTO studentDTO, BindingResult bindingResult, @PathVariable("id") int id) {
        studentDTO.setId(id);
        if (bindingResult.hasErrors()) {
            model.addAttribute("student", studentDTO);
            model.addAttribute("groups",groupService.findAll());
            return "students/edit_student";
        }
        studentService.update(studentDTO);
        return "redirect:/students/1";
    }

    @GetMapping("{id}/delete")
    public String deleteStudent(@PathVariable("id") int id) {
        studentService.delete(id);
        return "redirect:/students";
    }


}
