package ua.com.foxminded.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.com.foxminded.domain.entity.Group;
import ua.com.foxminded.domain.dto.LessonInfo;
import ua.com.foxminded.service.ClassRoomService;
import ua.com.foxminded.service.LessonService;

import javax.validation.Valid;

@Controller
@RequestMapping("/lessons")
@AllArgsConstructor
public class LessonController {
    private final LessonService lessonService;
    private final ClassRoomService classRoomService;


    @GetMapping
    public String showAll(Model model) {
        model.addAttribute("lessons", lessonService.findAll());
        return "/lessons/index";
    }

    @GetMapping("/new")
    public String showAdd(Model model) {
        model.addAttribute("lesson", new LessonInfo());
        model.addAttribute("class",classRoomService.findAll());
        return "lessons/new_lesson";
    }

    @PostMapping
    public String addLesson(Model model,@ModelAttribute("lesson") @Valid LessonInfo lessonInfo, BindingResult bindingResult, @ModelAttribute ("group")Group group) {
        if(bindingResult.hasErrors()){
            model.addAttribute("lesson",lessonInfo);
            model.addAttribute("class",classRoomService.findAll());
            return "lessons/new_lesson";
        }
        lessonService.save(lessonInfo);
        return "redirect:/lessons";
    }

    @GetMapping("{id}/edit")
    public String showEdit(@PathVariable("id") int id, Model model) {
        model.addAttribute("lesson", lessonService.findById(id));
        model.addAttribute("class",classRoomService.findAll());
        return "lessons/edit_lesson";
    }

    @PostMapping("{id}/edit")
    public String editLesson(Model model, @PathVariable("id") int id, @ModelAttribute("lesson") @Valid LessonInfo lessonInfo, BindingResult bindingResult) {
        lessonInfo.setLessonId(id);
        if(bindingResult.hasErrors()){
            model.addAttribute("lesson",lessonInfo);
            model.addAttribute("class",classRoomService.findAll());
            return "lessons/edit_lesson";
        }
        lessonService.update(lessonInfo);
        return "redirect:/lessons";
    }

    @GetMapping("{id}/delete")
    public String deleteLesson(@PathVariable("id") int id) {
        lessonService.delete(id);
        return "redirect:/lessons";
    }


}
