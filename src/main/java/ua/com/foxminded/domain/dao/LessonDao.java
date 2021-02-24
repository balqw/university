package ua.com.foxminded.domain.dao;

import ua.com.foxminded.domain.entity.LessonEntity;

import java.util.List;

public interface LessonDao extends CrudOperation<LessonEntity, Integer> {

    List<LessonEntity> findByGroup(Integer studentGroup);

}
