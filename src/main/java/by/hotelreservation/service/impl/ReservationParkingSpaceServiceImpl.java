package by.hotelreservation.service.impl;

import by.hotelreservation.bean.ReservationParkingSpace;
import by.hotelreservation.builder.ParkingSpaceBuilder;
import by.hotelreservation.builder.ReservationBuilder;
import by.hotelreservation.builder.ReservationParkingSpaceBuilder;
import by.hotelreservation.dao.ReservationParkingSpaceDao;
import by.hotelreservation.dao.impl.ReservationParkingSpaceDaoImpl;
import by.hotelreservation.dao.exception.DAOException;
import by.hotelreservation.service.AbstractService;
import by.hotelreservation.service.CrudService;
import by.hotelreservation.service.exception.ServiceException;

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
        }catch (DAOException e){
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
        }catch (DAOException e){
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
        }catch (DAOException e){
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
        }catch (DAOException e){
            throw new ServiceException(e);
        }finally {
            closeConnection(connection);
        }
    }

    public ReservationParkingSpace buildEntity(Map<String, String[]> params) throws ServiceException {
        return new ReservationParkingSpaceBuilder()
                .reservation(new ReservationBuilder().id(Integer.parseInt(params.get("idReservation")[0])).build())
                .parkingSpace(new ParkingSpaceBuilder().id(Integer.parseInt(params.get("idParkingSpace")[0])).build())
                .build();
    }

    @Override
    public ReservationParkingSpace getLastInsertedEntity() throws ServiceException {
        Connection connection = null;
        try {
            connection = getConnection();
            return reservationParkingSpaceDao.getLastInsertedReservationParkingSpace(connection);
        }catch (DAOException e){
            throw new ServiceException(e);
        }finally {
            closeConnection(connection);
        }
    }

}