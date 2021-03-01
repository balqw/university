package ua.com.foxminded.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.com.foxminded.domain.dto.EducatorDTO;
import ua.com.foxminded.domain.entity.EducatorEntity;
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
        model.addAttribute("educators",educatorService.findAll());
        return "/educators/index";
    }

    @GetMapping("/new")
    public String showAdd(Model model){
        model.addAttribute("educator",new EducatorDTO());
        return "/educators/new_educator";
    }

    @PostMapping
    public String addEducator(@ModelAttribute("educator") EducatorDTO educatorDTO){
        educatorService.save(educatorDTO);
        return "redirect:/educators";
    }

    @GetMapping("{id}/edit")
    public String showEdit(@PathVariable("id") int id, Model model){
        model.addAttribute("educator", educatorService.findById(id));
        return "/educators/edit_educator";
    }

    @PostMapping("{id}/edit")
    public String editEducator(@ModelAttribute("educator") EducatorDTO educatorDTO, @PathVariable("id") int id){
        educatorDTO.setEducatorId(id);
        educatorService.update(educatorDTO);
        idCardService.update(educatorDTO.getIdCardDTO());
        return "redirect:/educators";
    }

    @GetMapping("{id}/delete")
    public String deleteEducator(@PathVariable("id") int id){
        educatorService.delete(id);
        return "redirect:/educators";
    }




}
