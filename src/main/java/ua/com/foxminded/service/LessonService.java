package ua.com.foxminded.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.foxminded.domain.entity.ClassRoomEntity;
import ua.com.foxminded.domain.entity.LessonEntity;
import ua.com.foxminded.domain.dto.LessonInfo;
import ua.com.foxminded.domain.exceptions.NotFoundException;
import ua.com.foxminded.domain.mappers.LessonMapper;
import ua.com.foxminded.repository.ClassRoomRepository;
import ua.com.foxminded.repository.LessonRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class LessonService {
    private final LessonMapper lessonMapper;
    private final ClassRoomRepository classRoomRepository;
    private final LessonRepository lessonRepository;

    @Transactional
    public LessonInfo save(LessonInfo lessonInfo) {
        setRoom(lessonInfo);
        LessonEntity entity = lessonMapper.toEntity(lessonInfo);
        if(lessonRepository.existsLessonEntityByTitleAndStartLesson(entity.getTitle(),entity.getStartLesson()))
            throw new IllegalArgumentException("lesson already exist");
        lessonRepository.save(entity);
        return lessonInfo;
    }

    @Transactional
    public Page<LessonInfo> findAllPage(Pageable pageable) {
        return lessonRepository.findAll(pageable).map(lessonMapper::toDto);
    }

    @Transactional
    public List<LessonInfo> findAll() {
        return lessonRepository.findAll().stream().map(lessonMapper::toDto).collect(Collectors.toList());
    }

    @Transactional
    public LessonInfo findById(Integer id) {
        return lessonMapper.toDto(lessonRepository.findById(id)
                .orElseThrow(()-> new NotFoundException(format("Not found lesson with id %d", id))));
    }

    @Transactional
    public LessonInfo update(LessonInfo lessonInfo) {
        setRoom(lessonInfo);
        LessonEntity entity = lessonMapper.toEntity(lessonInfo);
        lessonRepository.save(entity);
        return lessonInfo;
    }

    @Transactional
    public void setRoom(LessonInfo lessonInfo) {
        try {
            ClassRoomEntity classRoom = classRoomRepository.findClassRoomEntityByNumber(lessonInfo.getClassRoom().getNumber());
            lessonInfo.setClassRoom(classRoom);
        } catch (RuntimeException e) {
            throw new IllegalArgumentException("class not exist");
        }
    }

    @Transactional
    public void delete(Integer id) {
        lessonRepository.deleteById(id);
    }
}
