package by.hotelreservation.dao;

import by.hotelreservation.exception.DAOException;

import java.util.List;

public interface EntityDao<T> {

    List<T> getAll() throws DAOException;
    void add(T entity) throws DAOException;
    void remove(int id) throws DAOException;
    void update(T entity) throws DAOException;
    T getById(int id) throws DAOException;
}
