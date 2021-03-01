package ua.com.foxminded.domain.mappers;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ua.com.foxminded.domain.dto.EducatorDTO;
import ua.com.foxminded.domain.entity.EducatorEntity;
import ua.com.foxminded.domain.entity.IdCardEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EducatorMapper {
    @Mapping(target = "name", source = "firstName")
    @Mapping(target = "surname", source = "lastName")
    @Mapping(target = "idCardDTO", source = "idCard")
    EducatorDTO toDto(EducatorEntity entity);

    @InheritInverseConfiguration
    EducatorEntity toEntity(EducatorDTO dto);

    List<EducatorDTO> toDtos(List<EducatorEntity>entities);
    List<EducatorEntity> toEntity(List<EducatorDTO>dtos);

}
