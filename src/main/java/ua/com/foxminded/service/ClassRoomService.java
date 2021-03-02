package ua.com.foxminded.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.foxminded.domain.dto.ClassRoomDTO;
import ua.com.foxminded.domain.entity.ClassRoomEntity;
import ua.com.foxminded.domain.mappers.ClassroomMapper;
import ua.com.foxminded.repository.ClassRoomRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class ClassRoomService{
    private final ClassRoomRepository repository;
    private final ClassroomMapper classroomMapper;

    @Transactional
    public ClassRoomDTO save(ClassRoomDTO classRoomDTO) {
        ClassRoomEntity roomEntity = classroomMapper.toEntity(classRoomDTO);
        if(repository.existsClassRoomEntitiesByNumber(roomEntity.getNumber()))
            throw new IllegalArgumentException(format("class with number - %d exist",roomEntity.getNumber()));
        repository.save(roomEntity);
        return classRoomDTO;
    }

    @Transactional
    public List<ClassRoomDTO> findAll() {
        List<ClassRoomDTO>rooms = classroomMapper.toDtos(repository.findAll());
        rooms.sort(Comparator.comparing(ClassRoomDTO::getNumber));
        return rooms;
    }

    @Transactional
    public ClassRoomDTO update(ClassRoomDTO roomDTO) {
        ClassRoomEntity roomEntity = classroomMapper.toEntity(roomDTO);
        repository.save(roomEntity);
        return roomDTO;
    }

    @Transactional
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Transactional
    public ClassRoomDTO findById(Integer id) {
        return classroomMapper.toDto(repository.findById(id)
                .orElseThrow(()->new IllegalArgumentException(format("Not found class with id %d",id))));
    }
}
