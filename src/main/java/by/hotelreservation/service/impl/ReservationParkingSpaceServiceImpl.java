package by.hotelreservation.service.impl;

import by.hotelreservation.bean.entity.ReservationParkingSpace;
import by.hotelreservation.builder.ParkingSpaceBuilder;
import by.hotelreservation.builder.ReservationBuilder;
import by.hotelreservation.builder.ReservationParkingSpaceBuilder;
import by.hotelreservation.dao.ReservationParkingSpaceDao;
import by.hotelreservation.dao.impl.ReservationParkingSpaceDaoImpl;
import by.hotelreservation.exception.DAOException;
import by.hotelreservation.exception.ServiceException;
import by.hotelreservation.service.AbstractService;
import by.hotelreservation.service.CrudService;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public class ReservationParkingSpaceServiceImpl extends AbstractService implements CrudService<ReservationParkingSpace> {
    ReservationParkingSpaceDao reservationParkingSpaceDao = new ReservationParkingSpaceDaoImpl();

    public List<ReservationParkingSpace> getAll() throws ServiceException {
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

    public List<ReservationParkingSpace> add(ReservationParkingSpace entity) throws ServiceException {
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

    public void delete(ReservationParkingSpace reservationParkingSpace) throws ServiceException {
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

    public void update(ReservationParkingSpace entity) throws ServiceException {
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

    public ReservationParkingSpace build(Map<String, String[]> params) throws ServiceException {
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