package by.hotelreservation.dao;

import by.hotelreservation.bean.User;
import by.hotelreservation.dao.exception.DAOException;

import java.sql.Connection;

public interface AuthDao {
    User authorisation(String email, String password, Connection connection)throws DAOException;
}
