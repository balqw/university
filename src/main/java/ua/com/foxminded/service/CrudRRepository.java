package ua.com.foxminded.service;

import java.util.Optional;

public interface CrudRRepository<T, ID> {

    T save(T entity);

    Optional<T> findById(ID id);

    boolean existsById(ID id);

    Iterable<T> findAll();

    long count();

    void deleteById(ID id);

    void delete(T entity);

    void deleteAll();
}
