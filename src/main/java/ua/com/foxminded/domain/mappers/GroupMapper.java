package ua.com.foxminded.domain.mappers;

import org.mapstruct.Mapper;
import ua.com.foxminded.domain.entity.Group;
import ua.com.foxminded.domain.dto.GroupDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GroupMapper {
    GroupDTO toDto (Group entity);
    Group toEntity (GroupDTO groupDTO);

    List<GroupDTO> toDtos (List<Group>entities);
    List<GroupDTO> toEntities(List<GroupDTO>dtos);
}
