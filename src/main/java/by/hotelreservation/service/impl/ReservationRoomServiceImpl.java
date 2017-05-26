package by.hotelreservation.service.impl;

import by.hotelreservation.bean.ReservationRoom;
import by.hotelreservation.builder.ReservationBuilder;
import by.hotelreservation.builder.ReservationRoomBuilder;
import by.hotelreservation.builder.RoomBuilder;
import by.hotelreservation.dao.ReservationRoomDao;
import by.hotelreservation.dao.impl.ReservationRoomDaoImpl;
import by.hotelreservation.dao.exception.DAOException;
import by.hotelreservation.service.AbstractService;
import by.hotelreservation.service.CrudService;
import by.hotelreservation.service.exception.ServiceException;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public class ReservationRoomServiceImpl extends AbstractService implements CrudService<ReservationRoom> {
    ReservationRoomDao reservationRoomDao = new ReservationRoomDaoImpl();

    public List<ReservationRoom> getAllEntities() throws ServiceException {
        Connection connection = null;
        try {
            connection = getConnection();
            return reservationRoomDao.getReservationRooms(connection);
        }catch (DAOException e){
            throw new ServiceException(e);
        }finally {
            closeConnection(connection);
        }
    }

    public List<ReservationRoom> addEntity(ReservationRoom entity) throws ServiceException {
        List<ReservationRoom> reservationRooms;
        Connection connection = null;
        try {
            connection = getConnection();
            reservationRoomDao.addReservationRoom(entity,connection);
            reservationRooms = reservationRoomDao.getReservationRooms(connection);
        }catch (DAOException e){
            throw new ServiceException(e);
        }finally {
            closeConnection(connection);
        }
        return reservationRooms;
    }

    public void removeEntity(ReservationRoom reservationRoom) throws ServiceException {
        Connection connection = null;
        try {
            connection = getConnection();
            reservationRoomDao.removeReservationRoom(reservationRoom,connection);
        }catch (DAOException e){
            throw new ServiceException(e);
        }finally {
            closeConnection(connection);
        }
    }

    public void updateEntity(ReservationRoom entity) throws ServiceException {
        Connection connection = null;
        try {
            connection = getConnection();
            reservationRoomDao.updateReservationRoom(entity,connection);
        }catch (DAOException e){
            throw new ServiceException(e);
        }finally {
            closeConnection(connection);
        }
    }

    public List<ReservationRoom> getReservationRoomByUser(int idUser) throws ServiceException{
        List<ReservationRoom> reservationRooms;
        Connection connection = null;
        try {
            connection = getConnection();
            reservationRooms = reservationRoomDao.getReservationRoomByUser(connection, idUser);
        }catch (DAOException e){
            throw new ServiceException(e);
        }finally {
            closeConnection(connection);
        }
        return reservationRooms;
    }

    public List<ReservationRoom> getReservationRoomByReservation(int idReservation) throws ServiceException{
        List<ReservationRoom> reservationRooms;
        Connection connection = null;
        try {
            connection = getConnection();
            reservationRooms = reservationRoomDao.getReservationRoomByReservation(connection, idReservation);
        }catch (DAOException e){
            throw new ServiceException(e);
        }finally {
            closeConnection(connection);
        }
        return reservationRooms;
    }

    public ReservationRoom buildEntity(Map<String, String[]> params) throws ServiceException {
        return new ReservationRoomBuilder()
                .reservation(new ReservationBuilder().id(Integer.parseInt(params.get("idReservation")[0])).build())
                .room(new RoomBuilder().id(Integer.parseInt(params.get("idRoom")[0])).build())
                .build();
    }

    public ReservationRoom getLastInsertedEntity() throws ServiceException {
        Connection connection = null;
        try {
            connection = getConnection();
            return reservationRoomDao.getLastInsertedReservationRoom(connection);
        }catch (DAOException e){
            throw new ServiceException(e);
        }finally {
            closeConnection(connection);
        }
    }

}