package ua.com.foxminded.domain.dao;

import java.util.List;

public interface CrudOperation<T, ID> {

    T create(T entity);
    List<T> readAll();
    T update (T entity);
    T delete (ID entity);

}
