package by.hotelreservation.dao;

import by.hotelreservation.bean.entity.ParkingSpace;
import by.hotelreservation.exception.DAOException;

import java.sql.Connection;
import java.util.List;

public interface ParkingSpaceDao {
    List<String> getParkingSpaceHeaders(Connection connection) throws DAOException;
    List<ParkingSpace> getParkingSpaces(Connection connection) throws DAOException;
    void addParkingSpace(ParkingSpace parkingSpace, Connection connection) throws DAOException;
    void removeParkingSpace(ParkingSpace parkingSpace, Connection connection) throws DAOException;
    void updateParkingSpace(ParkingSpace parkingSpace, Connection connection) throws DAOException;
    ParkingSpace getLastInsertedParkingSpace(Connection connection) throws DAOException;
}
