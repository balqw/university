package ua.com.foxminded.restController;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.com.foxminded.domain.dto.GroupDTO;
import ua.com.foxminded.service.GroupService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class GroupController {
    private final GroupService groupService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/groups")
    public List<GroupDTO> findAll(){
        return groupService.findAll();
    }

    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("groups/{id}")
    public GroupDTO findById(@PathVariable("id") Integer id){
        return groupService.findById(id);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("groups/{id}")
    public GroupDTO update(@PathVariable("id") Integer id, @RequestBody GroupDTO groupDTO){
        groupDTO.setGroupId(id);
        return groupService.update(groupDTO);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("groups")
    public GroupDTO save (@RequestBody @Valid GroupDTO groupDTO){
        return groupService.save(groupDTO);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("groups/{id}")
    public void delete (@PathVariable("id") Integer id){
        groupService.delete(id);
    }
}
