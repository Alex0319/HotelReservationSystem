package com.netcracker.dao;

import com.netcracker.dao.bean.Reservation;
import com.netcracker.dao.exception.DaoException;

import java.sql.Connection;
import java.util.List;

public interface ReservationDao {
    List<String> getReservationHeaders(Connection connection) throws DaoException;
    List<Reservation> getAllReservations(Connection connection) throws DaoException;
    void addReservation(Reservation reservation, Connection connection) throws DaoException;
    void removeReservation(Reservation reservation, Connection connection) throws DaoException;
    void updateReservation(Reservation reservation, Connection connection) throws DaoException;
  }
