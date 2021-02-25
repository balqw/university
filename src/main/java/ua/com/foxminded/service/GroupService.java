package ua.com.foxminded.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.foxminded.domain.dao.GroupDao;

import ua.com.foxminded.domain.entity.Group;
import ua.com.foxminded.domain.dto.GroupDTO;
import ua.com.foxminded.domain.exceptions.NotFoundException;
import ua.com.foxminded.domain.mappers.GroupMapper;
import ua.com.foxminded.reposytory.GroupRepository;

import java.util.List;
import java.util.Optional;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
@Transactional
public class GroupService {
    private final GroupMapper groupMapper;
    private final GroupRepository groupRepository;

    @Transactional
    public List<Group> readAll(){
        return groupRepository.findAll();
    }

    @Transactional
    public GroupDTO findById(Integer id){
        Optional<Group>optional = groupRepository.findById(id);
        if(optional.isPresent()){
            return groupMapper.toDto(optional.get());
        }
        else
            throw new NotFoundException(format("Not found group with id %d", id));
    }

    @Transactional
    public void delete (Integer id){
        groupRepository.deleteById(id);
    }

    @Transactional
    public Group update (Group group){
        return groupRepository.save(group);
    }

    @Transactional
    public Group save(Group group){
        if(groupRepository.existsGroupByAbbreviate(group.getAbbreviate()))
            throw new IllegalArgumentException("group already exist");
        return groupRepository.save(group);
   }
}
