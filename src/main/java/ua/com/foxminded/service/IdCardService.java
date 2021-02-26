package ua.com.foxminded.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.foxminded.domain.entity.IdCardEntity;
import ua.com.foxminded.domain.exceptions.NotFoundException;
import ua.com.foxminded.repository.IdCardRepository;

import java.util.List;
import java.util.Optional;

import static java.lang.String.format;

@RequiredArgsConstructor
@Service
public class IdCardService {
    private final IdCardRepository repository;

    @Transactional
    public IdCardEntity save(IdCardEntity entity) {
        return repository.save(entity);
    }

    @Transactional
    public List<IdCardEntity> readAll() {
        return repository.findAll();
    }

    @Transactional
    public IdCardEntity findOne(Integer id) {
        Optional<IdCardEntity>optional = repository.findById(id);
        if(optional.isPresent())
            return optional.get();
        else
            throw new NotFoundException(format("Not found idCard with id %d", id));
    }

    @Transactional
    public IdCardEntity update(IdCardEntity entity) {
        return repository.save(entity);
    }

    @Transactional
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
