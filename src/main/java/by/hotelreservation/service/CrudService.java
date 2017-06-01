package by.hotelreservation.service;

import by.hotelreservation.exception.ServiceException;

import java.util.List;
import java.util.Map;

public interface CrudService<T> {
    List<T> getAll() throws ServiceException;
    List<T> add(T entity) throws ServiceException;
    void delete(T entity) throws ServiceException;
    void update(T entity) throws ServiceException;
    T build(Map<String, String[]> params) throws ServiceException;
    T getLastInsertedEntity() throws ServiceException;
}