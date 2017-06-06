package by.hotelreservation.dao;

import by.hotelreservation.bean.entity.User;
import by.hotelreservation.exception.DAOException;

public interface UserDao extends EntityDao<User> {
    User checkUserEmail(String email) throws DAOException;
    User checkUser(String email, String password) throws DAOException;
}
