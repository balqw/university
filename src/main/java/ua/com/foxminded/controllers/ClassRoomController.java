package ua.com.foxminded.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.com.foxminded.domain.dto.ClassRoomDTO;
import ua.com.foxminded.domain.entity.ClassRoomEntity;
import ua.com.foxminded.service.ClassRoomService;

import javax.validation.Valid;

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
        model.addAttribute("classrooms",classRoomService.findAll());
        return "class/index";
    }

    @GetMapping("/new")
    public String showAdd(Model model){
        model.addAttribute("classroom", new ClassRoomDTO());
        return "class/new_classroom";
    }

    @PostMapping
    public String addClassRoom(@ModelAttribute("classroom") @Valid ClassRoomDTO roomDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){

            return "class/new_classroom";
        }
        classRoomService.save(roomDTO);
        return "redirect:/classrooms";
    }

    @GetMapping("{id}/edit")
    public String showEdit(@PathVariable("id") int id, Model model){
        model.addAttribute("classroom",classRoomService.findById(id));
        return "class/edit_classroom";
    }

    @PostMapping("{id}/edit")
    public String editClassRoom(Model model, @ModelAttribute("classroom") @Valid ClassRoomDTO roomDTO, BindingResult bindingResult,@PathVariable("id") int id){
        roomDTO.setId(id);
        if(bindingResult.hasErrors()){
            model.addAttribute("classroom", roomDTO);
            return "class/edit_classroom";
        }
        classRoomService.update(roomDTO);
        return "redirect:/classrooms";
    }

    @GetMapping("{id}/delete")
    public String deleteClassRoom(@PathVariable("id") int id){
        classRoomService.delete(id);
        return "redirect:/classrooms";
    }
}
