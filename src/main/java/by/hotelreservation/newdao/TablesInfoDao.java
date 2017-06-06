package by.hotelreservation.newdao;

import by.hotelreservation.exception.DAOException;

import java.util.List;

public interface TablesInfoDao {
    List<String> getNamesTables() throws DAOException;
}
