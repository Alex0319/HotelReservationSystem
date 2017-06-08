package by.hotelreservation.service;

import by.hotelreservation.bean.entity.AbstractEntity;
import by.hotelreservation.exception.ServiceException;

import java.util.List;
import java.util.Map;

public interface CrudService<T> {
    List<?> getAll() throws ServiceException;
    AbstractEntity getById(int id) throws ServiceException;
    List<T> add(T entity) throws ServiceException;
    void delete(T entity) throws ServiceException;
    void update(T entity) throws ServiceException;
    T build(Map<String, String[]> params) throws ServiceException;
}