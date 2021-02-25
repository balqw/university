package ua.com.foxminded.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.foxminded.domain.dao.ClassRoomDao;
import ua.com.foxminded.domain.entity.ClassRoomEntity;
import ua.com.foxminded.domain.exceptions.NotFoundException;
import ua.com.foxminded.reposytory.ClassRoomRepository;

import java.util.List;
import java.util.Optional;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class ClassRoomService{
    private final ClassRoomRepository repository;

    @Transactional
    public ClassRoomEntity save(ClassRoomEntity entity) {
        if(repository.existsClassRoomEntitiesByNumber(entity.getNumber()))
            throw new IllegalArgumentException(format("class with number - %d exist",entity.getNumber()));
       return repository.save(entity);
    }

    @Transactional
    public List<ClassRoomEntity> readAll() {
        return repository.findAll();
    }

    @Transactional
    public ClassRoomEntity findOne(Integer id) {
        Optional<ClassRoomEntity>optional = repository.findById(id);
        if(optional.isPresent())
            return optional.get();
        else
           throw  new NotFoundException(format("did't find class with id %d", id));
    }

    @Transactional
    public ClassRoomEntity update(ClassRoomEntity entity) {
          return repository.save(entity);
    }

    @Transactional
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
