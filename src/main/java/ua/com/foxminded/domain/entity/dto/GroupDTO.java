package ua.com.foxminded.domain.entity.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class GroupDTO {
    private Integer groupId;
    private String abbreviate;
    private String description;
}
