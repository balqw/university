package ua.com.foxminded.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.com.foxminded.domain.entity.ClassRoomEntity;
import ua.com.foxminded.service.ClassRoomService;

@Controller
@RequestMapping("/classrooms")
public class ClassRoomController {
    private final ClassRoomService classRoomService;

    @Autowired
    public ClassRoomController(ClassRoomService classRoomService) {
        this.classRoomService = classRoomService;
    }

    @GetMapping
    public String showAll(Model model){
        model.addAttribute("classrooms",classRoomService.readAll());
        return "class/index";
    }

    @GetMapping("/new")
    public String showAdd(Model model){
        model.addAttribute("classroom", new ClassRoomEntity());
        return "class/new_classroom";
    }

    @PostMapping
    public String addClassRoom(@ModelAttribute("classroom") ClassRoomEntity classRoomEntity){
        classRoomService.save(classRoomEntity);
        return "redirect:/classrooms";
    }

    @GetMapping("{id}/edit")
    public String showEdit(@PathVariable("id") int id, Model model){
        model.addAttribute("classroom",classRoomService.findOne(id));
        return "class/edit_classroom";
    }

    @PatchMapping("{id}")
    public String editClassRoom(@ModelAttribute("classroom") ClassRoomEntity classRoomEntity,@PathVariable("id") int id){
        classRoomEntity.setClassId(id);
        classRoomService.update(classRoomEntity);
        return "redirect:/classrooms";
    }

    @GetMapping("{id}/delete")
    public String deleteClassRoom(@PathVariable("id") int id){
        classRoomService.delete(id);
        return "redirect:/classrooms";
    }
}
