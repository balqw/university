package ua.com.foxminded.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.com.foxminded.domain.dto.EducatorDTO;
import ua.com.foxminded.domain.entity.EducatorEntity;
import ua.com.foxminded.service.EducatorService;
import ua.com.foxminded.service.IdCardService;

import javax.validation.Valid;

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
    public String addEducator(Model model, @ModelAttribute("educator") @Valid EducatorDTO educatorDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            model.addAttribute("educator",educatorDTO);
            return "/educators/new_educator";
        }
        educatorService.save(educatorDTO);
        return "redirect:/educators";
    }

    @GetMapping("{id}/edit")
    public String showEdit(@PathVariable("id") int id, Model model){
        model.addAttribute("educator", educatorService.findById(id));
        return "/educators/edit_educator";
    }

    @PostMapping("{id}/edit")
    public String editEducator(Model model, @ModelAttribute("educator") @Valid EducatorDTO educatorDTO, BindingResult bindingResult,@PathVariable("id") int id){
        educatorDTO.setEducatorId(id);
        if(bindingResult.hasErrors()){
            model.addAttribute("educator",educatorDTO);
            return "/educators/edit_educator";
        }
        educatorService.update(educatorDTO);
        return "redirect:/educators";
    }

    @GetMapping("{id}/delete")
    public String deleteEducator(@PathVariable("id") int id){
        educatorService.delete(id);
        return "redirect:/educators";
    }




}
