package com.netcracker.dao;

import com.netcracker.dao.bean.Room;
import com.netcracker.dao.exception.DaoException;

import java.util.List;

public interface RoomDao {
    List<Room> getRooms() throws DaoException;
    void addRoom(Room room) throws DaoException;
    void removeRoom(Room room) throws DaoException;
    void updateRoom(Room room) throws DaoException;
}
