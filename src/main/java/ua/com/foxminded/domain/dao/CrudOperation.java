package ua.com.foxminded.domain.dao;

import java.util.List;

public interface CrudOperation<T, ID> {

    T save(T entity);

    List<T> readAll();

    T findOne(ID id);

    T update(T entity);

    void delete(ID id);

    boolean exist(T entity);
}
