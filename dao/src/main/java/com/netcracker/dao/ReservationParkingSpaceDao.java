package com.netcracker.dao;

import com.netcracker.dao.bean.ReservationParkingSpace;
import com.netcracker.dao.exception.DaoException;

import java.sql.Connection;
import java.util.List;

public interface ReservationParkingSpaceDao {
    List<ReservationParkingSpace> getReservationParkingSpaces(Connection connection) throws DaoException;
    void addReservationParkingSpace(ReservationParkingSpace reservationParkingSpace, Connection connection) throws DaoException;
    void removeReservationParkingSpace(ReservationParkingSpace reservationParkingSpace, Connection connection) throws DaoException;
    void updateReservationParkingSpace(ReservationParkingSpace reservationParkingSpace, Connection connection) throws DaoException;
}
