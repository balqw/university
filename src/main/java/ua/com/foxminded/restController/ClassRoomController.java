package ua.com.foxminded.restController;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.com.foxminded.domain.dto.ClassRoomDTO;
import ua.com.foxminded.service.ClassRoomService;
import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ClassRoomController {
    private final ClassRoomService classRoomService;

    @ResponseStatus(HttpStatus.OK
    )
    @GetMapping("/classrooms")
    public Page<ClassRoomDTO> findAll(@PageableDefault Pageable pageable){
        return classRoomService.findAllPage(pageable);
    }

    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("classrooms/{id}")
    public ClassRoomDTO findById(@PathVariable("id") Integer id){
        return classRoomService.findById(id);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("classrooms/{id}")
    public ClassRoomDTO update(@PathVariable("id") Integer id, @RequestBody ClassRoomDTO classRoomDTO){
        classRoomDTO.setId(id);
        return classRoomService.update(classRoomDTO);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("classrooms")
    public ClassRoomDTO save (@RequestBody @Valid ClassRoomDTO classRoomDTO){
        return classRoomService.save(classRoomDTO);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("classrooms/{id}")
    public void delete (@PathVariable("id") Integer id){
        classRoomService.delete(id);
    }
}
