package ua.com.foxminded.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.com.foxminded.domain.entity.Group;
import ua.com.foxminded.domain.dto.GroupDTO;
import ua.com.foxminded.domain.exceptions.NotFoundException;
import ua.com.foxminded.domain.mappers.GroupMapper;
import ua.com.foxminded.repository.GroupRepository;

import java.util.List;
import java.util.Optional;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupMapper groupMapper;
    private final GroupRepository groupRepository;

    @Transactional
    public List<GroupDTO> findAll(){
        return groupMapper.toDtos(groupRepository.findAll());
    }

    @Transactional
    public GroupDTO findById(Integer id){
        return groupMapper.toDto(groupRepository.findById(id)
            .orElseThrow(()->new NotFoundException(format("Not found group with id %d", id))));
    }

    @Transactional
    public void delete(Integer id){
        groupRepository.deleteById(id);
    }

    @Transactional
    public GroupDTO update(GroupDTO dto){
        Group group = groupMapper.toEntity(dto);
        groupRepository.save(group);
        return dto;
    }

    @Transactional
    public GroupDTO save(GroupDTO dto){
        Group group = groupMapper.toEntity(dto);
        if(groupRepository.existsGroupByAbbreviate(group.getAbbreviate()))
            throw new IllegalArgumentException("group already exist");
        groupRepository.save(group);
        return dto;
   }
}
