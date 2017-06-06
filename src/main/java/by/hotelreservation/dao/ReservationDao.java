package by.hotelreservation.dao;

import by.hotelreservation.bean.entity.Reservation;
import by.hotelreservation.exception.DAOException;

import java.util.List;

public interface ReservationDao extends EntityDao<Reservation>{
    List<Reservation> getReservationByUser(int userId) throws DAOException;
}
