package ua.com.foxminded.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.com.foxminded.domain.dao.ClassRoomDao;
import ua.com.foxminded.domain.dao.GroupDao;
import ua.com.foxminded.domain.entity.Group;
import ua.com.foxminded.domain.entity.LessonEntity;
import ua.com.foxminded.domain.entity.dto.LessonInfo;
import ua.com.foxminded.service.LessonService;

@Controller
@RequestMapping("/lessons")
@AllArgsConstructor
public class LessonController {
    private final LessonService lessonService;
    private final ClassRoomDao classRoomDao;
    private final GroupDao groupDao;

    @GetMapping
    public String showAll(Model model) {
        model.addAttribute("lessons", lessonService.readAll());
        return "/lessons/index";
    }

    @GetMapping("/new")
    public String showAdd(Model model) {
        model.addAttribute("lesson", new LessonInfo());
        model.addAttribute("class",classRoomDao.readAll());
        model.addAttribute("groups", groupDao.readAll());
        return "lessons/new_lesson";
    }

    @PostMapping
    public String addLesson(@ModelAttribute("lesson") LessonInfo lessonInfo, @ModelAttribute ("group")Group group) {
        lessonService.save(lessonInfo);
        if(lessonInfo.getGroupList().size()!=0){
            for(Group one : lessonInfo.getGroupList()){
                lessonService.setGroup(one.getGroupId(),lessonInfo.getLessonId());
            }
        }
        return "redirect:/lessons";
    }

    @GetMapping("{id}/edit")
    public String showEdit(@PathVariable("id") int id, Model model) {
        model.addAttribute("lesson", lessonService.findOne(id));
        model.addAttribute("class",classRoomDao.readAll());
        model.addAttribute("group", groupDao.readAll());
        return "lessons/edit_lesson";
    }

    @PatchMapping("{id}")
    public String editLesson(@PathVariable("id") int id, @ModelAttribute("lesson") LessonEntity lessonEntity) {
        lessonEntity.setLessonId(id);
        lessonService.update(lessonEntity);
        return "redirect:/lessons";
    }

    @GetMapping("{id}/delete")
    public String deleteLesson(@PathVariable("id") int id) {
        lessonService.delete(id);
        return "redirect:/lessons";
    }


}
