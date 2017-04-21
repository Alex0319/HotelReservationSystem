package com.netcracker.service;

import com.netcracker.service.exception.ServiceException;

import java.util.List;
import java.util.Map;

public interface CrudService<T> {
    List<T> getAllEntities() throws ServiceException;
    List<T> addEntity(T entity) throws ServiceException;
    void removeEntity(T entity) throws ServiceException;
    void updateEntity(T entity) throws ServiceException;
    T buildEntity(Map<String, String[]> params) throws ServiceException;
   // <T> getEntity(Integer id) throws ServiceException;
}
