package by.hotelreservation.dao;

import by.hotelreservation.bean.entity.User;
import by.hotelreservation.exception.DAOException;

import java.sql.Connection;

public interface AuthDao {
    User authorisation(String email, String password, Connection connection)throws DAOException;
}
