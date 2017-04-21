package com.netcracker.dao;


import com.netcracker.dao.bean.RoomType;
import com.netcracker.dao.exception.DaoException;

import java.sql.Connection;
import java.util.List;

public interface RoomTypeDao {
    List<String> getRoomTypeHeaders(Connection connection) throws DaoException;
    List<RoomType> getRoomTypes(Connection connection) throws DaoException;
    void addRoomType(RoomType roomType, Connection connection) throws DaoException;
    void removeRoomType(RoomType roomType, Connection connection) throws DaoException;
    void updateRoomType(RoomType roomType, Connection connection) throws DaoException;
}
