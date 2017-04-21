package com.netcracker.dao;

import com.netcracker.dao.bean.User;
import com.netcracker.dao.exception.DaoException;

import java.sql.Connection;
import java.util.List;

public interface UserDao {
    List<String> getUserHeaders(Connection connection) throws DaoException;
    List<User> getUsers(Connection connection) throws DaoException;
    void addUser(User user, Connection connection) throws DaoException;
    void removeUser(User user, Connection connection) throws DaoException;
    void updateUser(User user, Connection connection) throws DaoException;
    User getUser(Integer id, Connection connection) throws DaoException;
}
