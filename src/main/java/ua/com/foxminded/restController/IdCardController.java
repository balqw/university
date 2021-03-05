package ua.com.foxminded.restController;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.com.foxminded.domain.dto.IdCardDTO;
import ua.com.foxminded.service.IdCardService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class IdCardController {
    IdCardService idCardService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/idcards")
    public List<IdCardDTO> findAll(){
        return idCardService.findAll();
    }

    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("idcards/{id}")
    public IdCardDTO findById(@PathVariable("id") Integer id){
        return idCardService.findById(id);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("idcards/{id}")
    public IdCardDTO update(@PathVariable("id") Integer id, @RequestBody @Valid IdCardDTO idCardDTO){
        idCardDTO.setCardId(id);
        return idCardService.update(idCardDTO);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("idcards")
    public IdCardDTO save (@RequestBody @Valid IdCardDTO idCardDTO){
        return idCardService.save(idCardDTO);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("idcards/{id}")
    public void delete(@PathVariable("id") Integer id){
        idCardService.deleteById(id);
    }
}
