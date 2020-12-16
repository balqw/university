package ua.com.foxminded.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.com.foxminded.domain.entity.EducatorEntity;
import ua.com.foxminded.domain.entity.IdCardEntity;
import ua.com.foxminded.service.EducatorService;
import ua.com.foxminded.service.IdCardService;

@Controller
@RequestMapping("/educators")
public class EducatorController {

    private final EducatorService educatorService;
    private final IdCardService idCardService;

    @Autowired
    public EducatorController(EducatorService educatorService, IdCardService idCardService) {
        this.educatorService = educatorService;
        this.idCardService = idCardService;
    }

    @GetMapping
    public String showAll(Model model){
        model.addAttribute("educators",educatorService.readAll());
        return "/educators/index";
    }

    @GetMapping("/new")
    public String showAdd(Model model){
        model.addAttribute("educator",new EducatorEntity());
        return "/educators/new_educator";
    }

    @PostMapping
    public String addEducator(@ModelAttribute("educator") EducatorEntity educatorEntity){
        educatorService.save(educatorEntity);
        return "redirect:/educators";
    }

    @GetMapping("{id}/edit")
    public String showEdit(@PathVariable("id") int id, Model model){
        model.addAttribute("educator", educatorService.findOne(id));
        return "/educators/edit_educator";
    }

    @PatchMapping("{id}")
    public String editEducator(@ModelAttribute("educator") EducatorEntity educatorEntity,@PathVariable("id") int id){
        educatorEntity.setEducatorId(id);
        educatorService.update(educatorEntity);
        idCardService.update(educatorEntity.getIdCard());
        return "redirect:/educators";
    }

    @GetMapping("{id}/delete")
    public String deleteEducator(@PathVariable("id") int id){
        educatorService.delete(id);
        return "redirect:/educators";
    }




}
