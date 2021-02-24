package ua.com.foxminded.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.com.foxminded.domain.entity.Group;
import ua.com.foxminded.service.GroupService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/groups")
public class GroupController {

    private final GroupService groupService;

    @GetMapping
    public String readAll(Model model){
        model.addAttribute("groups",groupService.readAll());
        return "groups/index";
    }

    @GetMapping("/new")
    public String getNewGroup(Model model){
        model.addAttribute("group_",new Group());
        return "groups/new_group";
    }

    @PostMapping
    public String creatGroup(@ModelAttribute("_group") Group group){
        groupService.save(group);
        return "redirect:/groups";
    }

    @GetMapping("{id}/edit")
    public String getEditGroup(@PathVariable("id") Integer id, Model model){
        model.addAttribute("group", groupService.findById(id));
        return "groups/edit_group";
    }

    @PatchMapping("{id}")
    public String updateGroup(@ModelAttribute("group") Group group, @PathVariable("id") Integer id ){
        group.setGroupId(id);
        groupService.update(group);
        return "redirect:/groups";
    }

    @DeleteMapping("{id}/delete")
    public String deleteGroup(@PathVariable("id") Integer id){
        groupService.delete(id);
        return "redirect:groups";
    }

}
