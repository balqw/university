package ua.com.foxminded.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.foxminded.domain.dao.GroupDao;

import ua.com.foxminded.domain.entity.Group;
import ua.com.foxminded.domain.dto.GroupDTO;
import ua.com.foxminded.domain.mappers.GroupMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class GroupService {
    private final GroupMapper groupMapper;
    private final GroupDao groupDao;

    @Transactional
    public List<Group> readAll(){
        return groupDao.readAll();
    }

    @Transactional
    public GroupDTO findById(Integer id){
        Group group = groupDao.findOne(id);
        return groupMapper.toDto(group);
    }

    @Transactional
    public void delete (Integer id){
        groupDao.delete(id);
    }

    @Transactional
    public Group update (Group group){
        return groupDao.save(group);
    }

    @Transactional
    public Group save(Group group){

        if(groupDao.exist(group))
            throw new IllegalArgumentException("group already exist");
        return groupDao.save(group);
   }
}
