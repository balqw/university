package ua.com.foxminded.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.com.foxminded.domain.dao.StudentGroupDao;

import ua.com.foxminded.domain.entity.Group;
import ua.com.foxminded.domain.entity.dto.StudentGroupDTO;
import ua.com.foxminded.domain.entity.mappers.StudentGroupMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentGroupService {
    private final StudentGroupMapper groupMapper;
    private final StudentGroupDao studentGroupDao;

    public List<Group> readAll(){
        return studentGroupDao.readAll();
    }

    public StudentGroupDTO findById(Integer id){
        return groupMapper.toDto((studentGroupDao.findOne(id)));
    }

    public void delete (Integer id){
        studentGroupDao.delete(id);
    }

    public StudentGroupDTO update(StudentGroupDTO studentGroupDTO){
        return groupMapper.toDto(studentGroupDao.update(groupMapper.toEntity(studentGroupDTO)));
    }

    public StudentGroupDTO save (StudentGroupDTO studentGroupDTO){
        Group entity = groupMapper.toEntity(studentGroupDTO);
        entity = studentGroupDao.save(entity);
        return groupMapper.toDto(entity);
    }
}
