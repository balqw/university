package ua.com.foxminded.domain.mappers;

import org.mapstruct.Mapper;
import ua.com.foxminded.domain.dto.IdCardDTO;
import ua.com.foxminded.domain.entity.IdCardEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IdCardMapper {
    IdCardDTO toDto (IdCardEntity entity);
    IdCardEntity toEntity (IdCardDTO dto);

    List<IdCardDTO> toDtos (List<IdCardEntity> entities);
    List<IdCardEntity> toEntities (List<IdCardDTO> dtos);
}
