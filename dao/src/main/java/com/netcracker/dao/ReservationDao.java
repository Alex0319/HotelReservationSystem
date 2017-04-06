package com.netcracker.dao;

import com.netcracker.dao.beans.Reservation;
import com.netcracker.dao.exception.DaoException;

import java.util.List;

public interface ReservationDao {
    List<Reservation> getAllReservationInfo() throws DaoException;
    void addReservationInfo(Reservation reservationInfo) throws DaoException;
    void removeReservationInfo(Reservation reservationInfo) throws DaoException;
    void updateReservationInfo(Reservation reservationInfo) throws DaoException;
}
