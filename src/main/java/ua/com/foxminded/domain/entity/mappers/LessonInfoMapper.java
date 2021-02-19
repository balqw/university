package ua.com.foxminded.domain.entity.mappers;

import org.mapstruct.Mapper;
import ua.com.foxminded.domain.entity.LessonEntity;
import ua.com.foxminded.domain.entity.dto.LessonInfo;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LessonInfoMapper {

    LessonInfo toDto(LessonEntity entity);

    LessonEntity toEntity(LessonInfo lessonInfo);

    List<LessonInfo> toDtos(List<LessonEntity>entities);
    List<LessonEntity> toEntities(List<LessonInfo>dtos);


}
