package com.netcracker.dao;

import com.netcracker.dao.bean.Reservation;
import com.netcracker.dao.exception.DaoException;

import java.util.List;

public interface ReservationDao {
    List<Reservation> getAllReservations() throws DaoException;
    void addReservation(Reservation reservationInfo) throws DaoException;
    void removeReservation(Reservation reservationInfo) throws DaoException;
    void updateReservation(Reservation reservationInfo) throws DaoException;
}
