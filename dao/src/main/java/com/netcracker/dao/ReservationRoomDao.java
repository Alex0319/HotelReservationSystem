package com.netcracker.dao;


import com.netcracker.dao.bean.ReservationRoom;
import com.netcracker.dao.exception.DaoException;

import java.sql.Connection;
import java.util.List;

public interface ReservationRoomDao {
    List<ReservationRoom> getReservationRooms(Connection connection) throws DaoException;
    void addReservationRoom(ReservationRoom reservationRoom, Connection connection) throws DaoException;
    void removeReservationRoom(ReservationRoom reservationRoom, Connection connection) throws DaoException;
    void updateReservationRoom(ReservationRoom reservationRoom, Connection connection) throws DaoException;
}
