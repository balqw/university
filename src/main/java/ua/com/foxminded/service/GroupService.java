package ua.com.foxminded.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.com.foxminded.domain.entity.Group;
import ua.com.foxminded.domain.dto.GroupDTO;
import ua.com.foxminded.domain.exceptions.NotFoundException;
import ua.com.foxminded.domain.mappers.GroupMapper;
import ua.com.foxminded.repository.GroupRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupMapper groupMapper;
    private final GroupRepository groupRepository;

    @Transactional
    public Page<GroupDTO> findAllPage(Pageable pageable){
        return groupRepository.findAll(pageable).map(groupMapper::toDto);
    }

    @Transactional
    public List<GroupDTO> findAll(){
        return groupRepository.findAll().stream().map(groupMapper::toDto).collect(Collectors.toList());
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
