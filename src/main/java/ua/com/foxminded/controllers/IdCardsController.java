package ua.com.foxminded.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.com.foxminded.domain.dto.IdCardDTO;
import ua.com.foxminded.domain.entity.IdCardEntity;
import ua.com.foxminded.service.IdCardService;

@Controller
@RequestMapping("/cards")
public class IdCardsController {

    private final IdCardService idCardService;

    @Autowired
    public IdCardsController(IdCardService idCardService) {
        this.idCardService = idCardService;
    }

    @GetMapping
    public String showAll(Model model){
        model.addAttribute("cards",idCardService.findAll());
        return "cards/index";
    }

    @GetMapping("/new")
    public String showAdd(Model model){
        model.addAttribute("card",new IdCardDTO());
        return "cards/new_card";
    }

    @PostMapping
    public String addCard(@ModelAttribute("card") IdCardDTO idcard){
        idCardService.save(idcard);
        return "redirect:/cards";
    }

    @GetMapping("{id}/edit")
    public String showEdit(@PathVariable("id")int id, Model model){
        model.addAttribute("card",idCardService.findById(id));
        return "cards/edit_card";
    }

    @PostMapping("{id}/edit")
    public String editCard(@ModelAttribute("card") IdCardDTO idcard, @PathVariable("id") int id){
        idcard.setCardId(id);
        idCardService.update(idcard);
        return "redirect:/cards";
    }

    @GetMapping("{id}/delete")
    public String deleteCard(@PathVariable("id") int id){
        idCardService.deleteById(id);
        return "redirect:/cards";
    }


}
