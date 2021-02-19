package ua.com.foxminded.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.com.foxminded.domain.dao.GroupDao;

import ua.com.foxminded.domain.entity.Group;
import ua.com.foxminded.domain.entity.dto.GroupDTO;
import ua.com.foxminded.domain.entity.mappers.GroupMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupMapper groupMapper;
    private final GroupDao groupDao;

    public List<Group> readAll(){
        return groupDao.readAll();
    }

    public GroupDTO findById(Integer id){
        Group group = groupDao.findOne(id);
        return groupMapper.toDto(group);
    }

    public void delete (Integer id){
        groupDao.delete(id);
    }

    public Group update (Group group){
        return groupDao.save(group);
    }

   public Group save(Group group){
        return groupDao.save(group);
   }
}
