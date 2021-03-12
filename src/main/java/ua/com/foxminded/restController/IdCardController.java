package ua.com.foxminded.restController;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.com.foxminded.domain.dto.IdCardDTO;
import ua.com.foxminded.service.IdCardServiceImpl;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class IdCardController {
    private final IdCardServiceImpl idCardServiceImpl;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/idcards")
    public List<IdCardDTO> findAll(){
        return idCardServiceImpl.findAll();
    }

    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("idcards/{id}")
    public IdCardDTO findById(@PathVariable("id") Integer id){
        return idCardServiceImpl.findById(id);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("idcards/{id}")
    public IdCardDTO update(@PathVariable("id") Integer id, @RequestBody @Valid IdCardDTO idCardDTO){
        idCardDTO.setCardId(id);
        return idCardServiceImpl.update(idCardDTO);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("idcards")
    public IdCardDTO save (@RequestBody @Valid IdCardDTO idCardDTO){
        return idCardServiceImpl.save(idCardDTO);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("idcards/{id}")
    public void delete(@PathVariable("id") Integer id){
        idCardServiceImpl.deleteById(id);
    }
}
