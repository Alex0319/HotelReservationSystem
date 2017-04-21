package com.netcracker.dao;

import com.netcracker.dao.bean.Room;
import com.netcracker.dao.exception.DaoException;

import java.sql.Connection;
import java.util.List;

public interface RoomDao {
    List<String> getRoomHeaders(Connection connection) throws DaoException;
    List<Room> getRooms(Connection connection) throws DaoException;
    void addRoom(Room room, Connection connection) throws DaoException;
    void removeRoom(Room room, Connection connection) throws DaoException;
    void updateRoom(Room room, Connection connection) throws DaoException;
}
