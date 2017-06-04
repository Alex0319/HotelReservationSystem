package by.hotelreservation.dao;

import by.hotelreservation.bean.entity.Room;
import by.hotelreservation.exception.DAOException;

import java.util.List;

public interface RoomDao {
    List<String> getRoomHeaders() throws DAOException;
    List<Room> getRooms() throws DAOException;
    void addRoom(Room room) throws DAOException;
    void removeRoom(Room room) throws DAOException;
    void updateRoom(Room room) throws DAOException;
    Room getLastInsertedRoom() throws DAOException;
    Room getRoom(int idRoom) throws DAOException;
}
