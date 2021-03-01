package ua.com.foxminded.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.foxminded.domain.dto.IdCardDTO;
import ua.com.foxminded.domain.entity.IdCardEntity;
import ua.com.foxminded.domain.exceptions.NotFoundException;
import ua.com.foxminded.domain.mappers.IdCardMapper;
import ua.com.foxminded.repository.IdCardRepository;

import java.util.List;
import java.util.Optional;

import static java.lang.String.format;

@RequiredArgsConstructor
@Service
public class IdCardService {
    private final IdCardRepository cardRepo;
    private final IdCardMapper idCardMapper;

    @Transactional
    public IdCardDTO save(IdCardDTO cardDto) {
        IdCardEntity cardEntity = idCardMapper.toEntity(cardDto);
        cardRepo.save(cardEntity);
        return cardDto;
    }

    @Transactional
    public List<IdCardDTO> findAll() {
        List<IdCardEntity> cardEntities = cardRepo.findAll();
        return idCardMapper.toDtos(cardEntities);
    }

    @Transactional
    public IdCardDTO findById(Integer id) {
        Optional<IdCardEntity>optional = cardRepo.findById(id);
        if(optional.isPresent())
            return idCardMapper.toDto(optional.get());
        else
            throw new NotFoundException(format("Not found idCard with id %d", id));
    }

    @Transactional
    public IdCardDTO update(IdCardDTO cardDto) {
        IdCardEntity cardEntity = idCardMapper.toEntity(cardDto);
        cardRepo.save(cardEntity);
        return cardDto;
    }

    @Transactional
    public void deleteById(Integer id) {
        cardRepo.deleteById(id);
    }
}
