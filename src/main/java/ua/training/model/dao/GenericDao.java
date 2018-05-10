package ua.training.model.dao;

import ua.training.controller.exceptions.LoginAlreadyExistsException;

import java.util.List;
import java.util.Optional;

/**
 * Максим
 * 06.05.2018
 */
public interface GenericDao<T> extends AutoCloseable{
    void create(T entity) throws LoginAlreadyExistsException;
    Optional<T> findById(int id);
    List<T> findAll();
    void update(T entity);
    void delete(int id);
    void close();
}
