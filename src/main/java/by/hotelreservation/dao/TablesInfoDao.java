package by.hotelreservation.dao;

import by.hotelreservation.exception.DAOException;

import java.sql.Connection;
import java.util.List;

public interface TablesInfoDao {
    List<String> getNamesTables(Connection connection) throws DAOException;
}
