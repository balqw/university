package ua.com.foxminded.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.com.foxminded.domain.dto.GroupDTO;
import ua.com.foxminded.service.GroupService;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/groups")
public class GroupsController {

    private final GroupService groupService;

    @GetMapping
    public String readAll(Model model){
        model.addAttribute("groups",groupService.findAll());
        return "groups/index";
    }

    @GetMapping("/new")
    public String getNewGroup(Model model){
        model.addAttribute("group_",new GroupDTO());
        return "groups/new_group";
    }

    @PostMapping
    public String creatGroup(@ModelAttribute("_group") @Valid GroupDTO group, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "groups/new_group";
        }
        groupService.save(group);
        return "redirect:/groups";
    }

    @GetMapping("{id}/edit")
    public String getEditGroup(@PathVariable("id") Integer id, Model model){
        model.addAttribute("group", groupService.findById(id));
        return "groups/edit_group";
    }

    @PostMapping("{id}/edit")
    public String updateGroup(Model model, @ModelAttribute("group") @Valid GroupDTO groupDTO,BindingResult bindingResult, @PathVariable("id") Integer id ){
        groupDTO.setGroupId(id);
        if(bindingResult.hasErrors()){
            model.addAttribute("group",groupDTO);
            return "groups/edit_group";
        }
        groupService.update(groupDTO);
        return "redirect:/groups";
    }

    @DeleteMapping("{id}/delete")
    public String deleteGroup(@PathVariable("id") Integer id){
        groupService.delete(id);
        return "redirect:groups";
    }

}
