package ua.com.foxminded.restController;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.com.foxminded.domain.dto.EducatorDTO;
import ua.com.foxminded.domain.dto.GroupDTO;
import ua.com.foxminded.service.EducatorService;
import ua.com.foxminded.service.GroupService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class EducatorController {
    private final EducatorService educatorService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/educators")
    public List<EducatorDTO> findAll(){
        return educatorService.findAll();
    }

    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("educators/{id}")
    public EducatorDTO findById(@PathVariable("id") Integer id){
        return educatorService.findById(id);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("educators/{id}")
    public EducatorDTO update(@PathVariable("id") Integer id, @RequestBody EducatorDTO educatorDTO){
        educatorDTO.setEducatorId(id);
        return educatorService.update(educatorDTO);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("educators")
    public EducatorDTO save (@RequestBody @Valid EducatorDTO educatorDTO){
        return educatorService.save(educatorDTO);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("educators/{id}")
    public void delete (@PathVariable("id") Integer id){
        educatorService.delete(id);
    }
}
