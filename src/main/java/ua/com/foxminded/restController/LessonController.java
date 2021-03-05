package ua.com.foxminded.restController;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.com.foxminded.domain.dto.LessonInfo;
import ua.com.foxminded.service.LessonService;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LessonController {
    private final LessonService lessonService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/lessons")
    public List<LessonInfo> findAll(){
        return lessonService.findAll();
    }

    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("lessons/{id}")
    public LessonInfo findById(@PathVariable("id") Integer id){
        return lessonService.findById(id);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("lessons/{id}")
    public void deleteById(@PathVariable("id") Integer id){
        lessonService.delete(id);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("lessons/{id}")
    public LessonInfo update(@PathVariable("id") Integer id, @RequestBody @Valid LessonInfo lessonInfo){
        lessonInfo.setLessonId(id);
        return lessonService.update(lessonInfo);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("lessons")
    public LessonInfo save(@RequestBody @Valid LessonInfo lessonInfo){
        return lessonService.save(lessonInfo);
    }

}
