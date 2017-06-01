package by.hotelreservation.dao;

import by.hotelreservation.bean.entity.Reservation;
import by.hotelreservation.exception.DAOException;

import java.sql.Connection;
import java.util.List;

public interface ReservationDao {
    List<String> getReservationHeaders(Connection connection) throws DAOException;
    List<Reservation> getAllReservations(Connection connection) throws DAOException;
    Reservation getReservation(Integer id, Connection connection) throws DAOException;
    void addReservation(Reservation reservation, Connection connection) throws DAOException;
    void removeReservation(Reservation reservation, Connection connection) throws DAOException;
    void updateReservation(Reservation reservation, Connection connection) throws DAOException;
    Reservation getLastInsertedReservation(Connection connection) throws DAOException;

}
