package com.netcracker.service.impl;

import com.netcracker.dao.ReservationDao;
import com.netcracker.dao.bean.Reservation;
import com.netcracker.dao.builder.DiscountBuilder;
import com.netcracker.dao.builder.ReservationBuilder;
import com.netcracker.dao.builder.UserBuilder;
import com.netcracker.dao.exception.DaoException;
import com.netcracker.dao.impl.ReservationDaoImpl;
import com.netcracker.service.AbstractService;
import com.netcracker.service.CrudServiceExtended;
import com.netcracker.service.exception.ServiceException;

import java.sql.Connection;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

public class ReservationServiceImpl extends AbstractService implements CrudServiceExtended<Reservation> {
    ReservationDao reservationDao = new ReservationDaoImpl();

    public List<String> getAllHeaders() throws ServiceException {
        Connection connection = null;
        try {
            connection = getConnection();
            return reservationDao.getReservationHeaders(connection);
        }catch (DaoException e){
            throw new ServiceException(e);
        }finally {
            closeConnection(connection);
        }
    }

    public List<Reservation> getAllEntities() throws ServiceException {
        Connection connection = null;
        try {
            connection = getConnection();
            return reservationDao.getAllReservations(connection);
        }catch (DaoException e){
            throw new ServiceException(e);
        }finally {
            closeConnection(connection);
        }
    }

    public List<Reservation> addEntity(Reservation entity) throws ServiceException {
        List<Reservation> reservations;
        Connection connection = null;
        try {
            connection = getConnection();
            reservationDao.addReservation(entity,connection);
            reservations = reservationDao.getAllReservations(connection);
        }catch (DaoException e){
            throw new ServiceException(e);
        }finally {
            closeConnection(connection);
        }
        return reservations;
    }

    public void removeEntity(Reservation reservation) throws ServiceException {
        Connection connection = null;
        try {
            connection = getConnection();
            reservationDao.removeReservation(reservation,connection);
        }catch (DaoException e){
            throw new ServiceException(e);
        }finally {
            closeConnection(connection);
        }
    }

    public void updateEntity(Reservation entity) throws ServiceException {
        Connection connection = null;
        try {
            connection = getConnection();
            reservationDao.updateReservation(entity,connection);
        }catch (DaoException e){
            throw new ServiceException(e);
        }finally {
            closeConnection(connection);
        }
    }

    public Reservation buildEntity(Map<String, String[]> params) throws ServiceException {
        Reservation reservation;
        try {
            reservation =  new ReservationBuilder().id(Integer.parseInt(params.get("id")[0]))
                    .dateIn(new Date(new SimpleDateFormat("MMM dd, yyyy").parse(params.get("dateIn")[0]).getTime()))
                    .dateOut(new Date(new SimpleDateFormat("MMM dd, yyyy").parse(params.get("dateOut")[0]).getTime()))
                    .costAdditionalServices(Integer.parseInt(params.get("costAdditionalServices")[0]))
                    .user(new UserBuilder().id(Integer.parseInt(params.get("id_user")[0])).build())
                    .discount(new DiscountBuilder().id(Integer.parseInt(params.get("id_discount")[0])).build())
                    .build();
        }catch (ParseException e){
            throw new ServiceException(e);
        }
        return reservation;
    }
}
