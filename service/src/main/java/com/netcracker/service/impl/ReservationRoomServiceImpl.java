package com.netcracker.service.impl;

import com.netcracker.dao.ReservationRoomDao;
import com.netcracker.dao.bean.ReservationRoom;
import com.netcracker.dao.builder.ReservationBuilder;
import com.netcracker.dao.builder.ReservationRoomBuilder;
import com.netcracker.dao.builder.RoomBuilder;
import com.netcracker.dao.exception.DaoException;
import com.netcracker.dao.impl.ReservationRoomDaoImpl;
import com.netcracker.service.AbstractService;
import com.netcracker.service.CrudService;
import com.netcracker.service.exception.ServiceException;

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
        }catch (DaoException e){
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
        }catch (DaoException e){
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
        }catch (DaoException e){
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
        }catch (DaoException e){
            throw new ServiceException(e);
        }finally {
            closeConnection(connection);
        }
    }

    public ReservationRoom buildEntity(Map<String, String[]> params) throws ServiceException {
        return new ReservationRoomBuilder()
                .reservation(new ReservationBuilder().id(Integer.parseInt(params.get("id_reservation")[0])).build())
                .room(new RoomBuilder().id(Integer.parseInt(params.get("id_room")[0])).build())
                .build();
    }
}
