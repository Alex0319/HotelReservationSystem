package com.netcracker.dao;

import com.netcracker.dao.bean.User;
import com.netcracker.dao.exception.DaoException;

import java.util.List;

public interface UserDao {
    List<User> getUsers() throws DaoException;
    void addUser(User user) throws DaoException;
    void removeUser(User user) throws DaoException;
    void updateUser(User user) throws DaoException;

}
