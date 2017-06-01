package by.hotelreservation.dao;

import by.hotelreservation.bean.entity.Room;
import by.hotelreservation.exception.DAOException;

import java.sql.Connection;
import java.util.List;

public interface RoomDao {
    List<String> getRoomHeaders(Connection connection) throws DAOException;
    List<Room> getRooms(Connection connection) throws DAOException;
    void addRoom(Room room, Connection connection) throws DAOException;
    void removeRoom(Room room, Connection connection) throws DAOException;
    void updateRoom(Room room, Connection connection) throws DAOException;
    Room getLastInsertedRoom(Connection connection) throws DAOException;
    Room getRoom(Connection connection, int idRoom) throws DAOException;
}
