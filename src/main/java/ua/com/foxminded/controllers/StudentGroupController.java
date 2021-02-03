package ua.com.foxminded.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.foxminded.service.StudentGroupService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/groups")
public class StudentGroupController {

    private final StudentGroupService groupService;

    @GetMapping
    public String readAll(Model model){
        model.addAttribute("studentGroups",groupService.readAll());
        return "group/index";
    }
}
