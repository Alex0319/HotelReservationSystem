package by.hotelreservation.dao;

import by.hotelreservation.bean.entity.User;
import by.hotelreservation.exception.DAOException;

import java.sql.Connection;
import java.util.List;

public interface UserDao {
    List<String> getUserHeaders(Connection connection) throws DAOException;
    List<User> getUsers(Connection connection) throws DAOException;
    void addUser(User user, Connection connection) throws DAOException;
    void removeUser(User user, Connection connection) throws DAOException;
    void updateUser(User user, Connection connection) throws DAOException;
    User getUser(Connection connection, int idUser) throws DAOException;
    User getLastInsertedUser(Connection connection) throws DAOException;

}
