package by.hotelreservation.dao;

import by.hotelreservation.dao.exception.DAOException;

import java.sql.Connection;
import java.util.List;

public interface TablesInfoDao {
    List<String> getNamesTables(Connection connection) throws DAOException;
}
