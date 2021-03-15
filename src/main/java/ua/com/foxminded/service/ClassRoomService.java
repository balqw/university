package ua.com.foxminded.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import java.util.stream.Collectors;

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
    public Page<ClassRoomDTO> findAllPage(Pageable pageable) {
        return repository.findAll(pageable).map(classroomMapper::toDto);
    }

    @Transactional
    public List<ClassRoomDTO> findAll() {
        return repository.findAll().stream().map(classroomMapper::toDto).collect(Collectors.toList());
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
