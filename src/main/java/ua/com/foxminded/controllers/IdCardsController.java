package ua.com.foxminded.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
        model.addAttribute("cards",idCardService.readAll());
        return "cards/index";
    }

    @GetMapping("/new")
    public String showAdd(Model model){
        model.addAttribute("card",new IdCardEntity());
        return "cards/new_card";
    }

    @PostMapping
    public String addCard(@ModelAttribute("card") IdCardEntity idCardEntity){
        idCardService.save(idCardEntity);
        return "redirect:/cards";
    }

    @GetMapping("{id}/edit")
    public String showEdit(@PathVariable("id")int id, Model model){
        model.addAttribute("card",idCardService.findOne(id));
        return "cards/edit_card";
    }

    @PatchMapping("{id}")
    public String editCard(@ModelAttribute("card") IdCardEntity idCardEntity,@PathVariable("id") int id){
        idCardEntity.setCardId(id);
        idCardService.update(idCardEntity);
        return "redirect:/cards";
    }

    @GetMapping("{id}/delete")
    public String deleteCard(@PathVariable("id") int id){
        idCardService.delete(id);
        return "redirect:/cards";
    }


}
