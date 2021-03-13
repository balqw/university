package ua.com.foxminded.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import ua.com.foxminded.domain.dto.IdCardDTO;
import ua.com.foxminded.domain.entity.IdCardEntity;

public interface IdCardService {
    Page<IdCardDTO> findPaginated(int pageNo, int pageSize, Sort sortBy);
}
