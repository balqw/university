package ua.com.foxminded.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.foxminded.domain.dto.EducatorDTO;
import ua.com.foxminded.domain.entity.EducatorEntity;
import ua.com.foxminded.domain.entity.IdCardEntity;
import ua.com.foxminded.domain.exceptions.NotFoundException;
import ua.com.foxminded.domain.mappers.EducatorMapper;
import ua.com.foxminded.repository.EducatorRepository;
import ua.com.foxminded.repository.IdCardRepository;

import java.util.List;
import java.util.Optional;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class EducatorService {
    private final EducatorRepository educatorRepository;
    private final IdCardRepository cardRepository;
    private final EducatorMapper educatorMapper;

    @Transactional
    public EducatorEntity save(EducatorDTO educatorDTO) {
        EducatorEntity educator = educatorMapper.toEntity(educatorDTO);
       if(educatorRepository.existsEducatorEntityByFirstNameAndAndFirstName(educator.getFirstName(),educator.getLastName()))
            throw new IllegalArgumentException("Educator already exist");
       idCardExistsAndValid(educator.getIdCard());
       return educatorRepository.save(educator);
    }

    @Transactional
    public void idCardExistsAndValid(IdCardEntity idCard) {
        if (idCard != null && idCard.getCardId() != null) {
            if (!cardRepository.existsById(idCard.getCardId()))
                throw new NotFoundException("Such idCard not found");
        }
    }

    @Transactional
    public List<EducatorDTO> findAll() {
        List<EducatorEntity>entities =  educatorRepository.findAll();
        return educatorMapper.toDtos(entities);
    }

    @Transactional
    public EducatorDTO findById(Integer id) {
        Optional<EducatorEntity>optional = educatorRepository.findById(id);
        if(optional.isPresent())
            return educatorMapper.toDto(optional.get());
        else
            throw new NotFoundException(format("Not found educator with id %d", id));
    }

    @Transactional
    public EducatorDTO update(EducatorDTO dto) {
        EducatorEntity educator = educatorMapper.toEntity(dto);
        educatorRepository.save(educator);
        return dto;
    }

    @Transactional
    public void delete(Integer id) {
        educatorRepository.deleteById(id);
    }

}
