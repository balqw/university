package ua.com.foxminded.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.com.foxminded.domain.dto.IdCardDTO;
import ua.com.foxminded.domain.entity.IdCardEntity;
import ua.com.foxminded.service.IdCardServiceImpl;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/cards")
public class IdCardsController {

    private final IdCardServiceImpl idCardServiceImpl;

    @Autowired
    public IdCardsController(IdCardServiceImpl idCardServiceImpl) {
        this.idCardServiceImpl = idCardServiceImpl;
    }

    @GetMapping("page/{pageNo}")
    public String showAllPageable(Model model, @PathVariable("pageNo") int pageNo, Sort sortBy){
        int pageSize = 5;
        Page<IdCardDTO> page = idCardServiceImpl.findPaginated(pageNo,pageSize, sortBy);
        List<IdCardDTO> listIdCards = page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("cards", listIdCards);
        return "cards/index";
    }

    @GetMapping
    public String showAll(Model model){
        model.addAttribute("cards", idCardServiceImpl.findAll());
        return "cards/index";
    }

    @GetMapping("/new")
    public String showAdd(Model model){
        model.addAttribute("card",new IdCardDTO());
        return "cards/new_card";
    }

    @PostMapping
    public String addCard(Model model, @ModelAttribute("card") @Valid IdCardDTO idcard, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            model.addAttribute("cards",idcard);
            return "cards/new_card";
        }
        idCardServiceImpl.save(idcard);
        return "redirect:/cards";
    }

    @GetMapping("{id}/edit")
    public String showEdit(@PathVariable("id")int id, Model model){
        model.addAttribute("card", idCardServiceImpl.findById(id));
        return "cards/edit_card";
    }

    @PostMapping("{id}/edit")
    public String editCard(Model model, @ModelAttribute("card") @Valid IdCardDTO idcard,BindingResult bindingResult, @PathVariable("id") int id){
        idcard.setCardId(id);
        if(bindingResult.hasErrors()){
            model.addAttribute("card",idcard);
            return "cards/edit_card";
        }
        idCardServiceImpl.update(idcard);
        return "redirect:/cards";
    }

    @GetMapping("{id}/delete")
    public String deleteCard(@PathVariable("id") int id){
        idCardServiceImpl.deleteById(id);
        return "redirect:/cards";
    }


}
