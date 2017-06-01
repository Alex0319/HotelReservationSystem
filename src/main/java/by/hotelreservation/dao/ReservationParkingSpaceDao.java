package by.hotelreservation.dao;

import by.hotelreservation.bean.entity.ReservationParkingSpace;
import by.hotelreservation.exception.DAOException;

import java.sql.Connection;
import java.util.List;

public interface ReservationParkingSpaceDao {
    List<ReservationParkingSpace> getReservationParkingSpaces(Connection connection) throws DAOException;
    void addReservationParkingSpace(ReservationParkingSpace reservationParkingSpace, Connection connection) throws DAOException;
    void removeReservationParkingSpace(ReservationParkingSpace reservationParkingSpace, Connection connection) throws DAOException;
    void updateReservationParkingSpace(ReservationParkingSpace reservationParkingSpace, Connection connection) throws DAOException;
    ReservationParkingSpace getLastInsertedReservationParkingSpace(Connection connection) throws DAOException;

}
