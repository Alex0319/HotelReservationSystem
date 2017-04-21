package com.netcracker.service.impl;

import com.netcracker.dao.ReservationParkingSpaceDao;
import com.netcracker.dao.bean.ReservationParkingSpace;
import com.netcracker.dao.builder.ParkingSpaceBuilder;
import com.netcracker.dao.builder.ReservationBuilder;
import com.netcracker.dao.builder.ReservationParkingSpaceBuilder;
import com.netcracker.dao.exception.DaoException;
import com.netcracker.dao.impl.ReservationParkingSpaceDaoImpl;
import com.netcracker.service.AbstractService;
import com.netcracker.service.CrudService;
import com.netcracker.service.exception.ServiceException;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public class ReservationParkingSpaceServiceImpl extends AbstractService implements CrudService<ReservationParkingSpace> {
    ReservationParkingSpaceDao reservationParkingSpaceDao = new ReservationParkingSpaceDaoImpl();

    public List<ReservationParkingSpace> getAllEntities() throws ServiceException {
        Connection connection = null;
        try {
            connection = getConnection();
            return reservationParkingSpaceDao.getReservationParkingSpaces(connection);
        }catch (DaoException e){
            throw new ServiceException(e);
        }finally {
            closeConnection(connection);
        }
    }

    public List<ReservationParkingSpace> addEntity(ReservationParkingSpace entity) throws ServiceException {
        List<ReservationParkingSpace> reservationParkingSpaces;
        Connection connection = null;
        try {
            connection = getConnection();
            reservationParkingSpaceDao.addReservationParkingSpace(entity,connection);
            reservationParkingSpaces = reservationParkingSpaceDao.getReservationParkingSpaces(connection);
        }catch (DaoException e){
            throw new ServiceException(e);
        }finally {
            closeConnection(connection);
        }
        return reservationParkingSpaces;
    }

    public void removeEntity(ReservationParkingSpace reservationParkingSpace) throws ServiceException {
        Connection connection = null;
        try {
            connection = getConnection();
            reservationParkingSpaceDao.removeReservationParkingSpace(reservationParkingSpace,connection);
        }catch (DaoException e){
            throw new ServiceException(e);
        }finally {
            closeConnection(connection);
        }
    }

    public void updateEntity(ReservationParkingSpace entity) throws ServiceException {
        Connection connection = null;
        try {
            connection = getConnection();
            reservationParkingSpaceDao.updateReservationParkingSpace(entity,connection);
        }catch (DaoException e){
            throw new ServiceException(e);
        }finally {
            closeConnection(connection);
        }
    }

    public ReservationParkingSpace buildEntity(Map<String, String[]> params) throws ServiceException {
        return new ReservationParkingSpaceBuilder()
                .reservation(new ReservationBuilder().id(Integer.parseInt(params.get("id_reservation")[0])).build())
                .parkingSpace(new ParkingSpaceBuilder().id(Integer.parseInt(params.get("id_parkingSpace")[0])).build())
                .build();
    }
}
