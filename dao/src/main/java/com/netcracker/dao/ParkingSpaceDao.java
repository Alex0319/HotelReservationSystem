package com.netcracker.dao;


import com.netcracker.dao.bean.ParkingSpace;
import com.netcracker.dao.exception.DaoException;

import java.sql.Connection;
import java.util.List;

public interface ParkingSpaceDao {
    List<String> getParkingSpaceHeaders(Connection connection) throws DaoException;
    List<ParkingSpace> getParkingSpaces(Connection connection) throws DaoException;
    void addParkingSpace(ParkingSpace parkingSpace, Connection connection) throws DaoException;
    void removeParkingSpace(ParkingSpace parkingSpace, Connection connection) throws DaoException;
    void updateParkingSpace(ParkingSpace parkingSpace, Connection connection) throws DaoException;
    ParkingSpace getParkingSpace(Integer id, Connection connection) throws DaoException;
}
