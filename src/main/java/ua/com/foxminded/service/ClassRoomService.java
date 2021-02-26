package ua.com.foxminded.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.foxminded.domain.entity.ClassRoomEntity;
import ua.com.foxminded.repository.ClassRoomRepository;

import java.util.List;

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
    public ClassRoomEntity update(ClassRoomEntity entity) {
          return repository.save(entity);
    }

    @Transactional
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Transactional
    public ClassRoomEntity findOne(Integer id) {
       return repository.findById(id)
               .orElseThrow(()->new IllegalArgumentException(""));}
}
