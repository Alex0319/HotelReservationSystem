package by.hotelreservation.dao;

import by.hotelreservation.bean.RoomType;
import by.hotelreservation.dao.exception.DAOException;

import java.sql.Connection;
import java.util.List;

public interface RoomTypeDao {
    List<String> getRoomTypeHeaders(Connection connection) throws DAOException;
    List<RoomType> getRoomTypes(Connection connection) throws DAOException;
    RoomType getRoomType(Connection connection, int idRoomType) throws DAOException;
    void addRoomType(RoomType roomType, Connection connection) throws DAOException;
    void removeRoomType(RoomType roomType, Connection connection) throws DAOException;
    void updateRoomType(RoomType roomType, Connection connection) throws DAOException;
    RoomType getLastInsertedRoomType(Connection connection) throws DAOException;
}
