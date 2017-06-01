package by.hotelreservation.service.impl;

import by.hotelreservation.bean.entity.Reservation;
import by.hotelreservation.bean.entity.ReservationRoom;
import by.hotelreservation.builder.*;
import by.hotelreservation.dao.ReservationDao;
import by.hotelreservation.dao.ReservationRoomDao;
import by.hotelreservation.dao.impl.ReservationDaoImpl;
import by.hotelreservation.dao.impl.ReservationRoomDaoImpl;
import by.hotelreservation.exception.DAOException;
import by.hotelreservation.exception.ServiceException;
import by.hotelreservation.exception.validateexception.IncorrectCostException;
import by.hotelreservation.exception.validateexception.IncorrectDateException;
import by.hotelreservation.service.AbstractService;
import by.hotelreservation.service.CrudServiceExtended;
import by.hotelreservation.service.validator.ValidatorReservation;

import java.sql.Connection;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

public class ReservationServiceImpl extends AbstractService implements CrudServiceExtended<Reservation> {
    private ReservationRoom reservationRoom;
    private ReservationDao reservationDao = new ReservationDaoImpl();

    public List<String> getAllHeaders() throws ServiceException {
        Connection connection = null;
        try {
            connection = getConnection();
            return reservationDao.getReservationHeaders(connection);
        } catch (DAOException e) {
            throw new ServiceException(e);
        } finally {
            closeConnection(connection);
        }
    }

    public List<Reservation> getAll() throws ServiceException {
        Connection connection = null;
        try {
            connection = getConnection();
            return reservationDao.getAllReservations(connection);
        } catch (DAOException e) {
            throw new ServiceException(e);
        } finally {
            closeConnection(connection);
        }
    }

    public Reservation getEntity(Integer id) throws ServiceException {
        Connection connection = null;
        Reservation reservation;
        try {
            connection = getConnection();
            reservation = reservationDao.getReservation(id, connection);
        } catch (DAOException e) {
            throw new ServiceException(e);
        } finally {
            closeConnection(connection);
        }
        return reservation;
    }

    public List<Reservation> add(Reservation entity) throws ServiceException {
        List<Reservation> reservations;
        Connection connection = null;
        try {
            connection = getConnection();
            reservationDao.addReservation(entity, connection);
            reservationRoom.setReservation(reservationDao.getLastInsertedReservation(connection));
            ReservationRoomDao reservationRoomDao = new ReservationRoomDaoImpl();
            reservationRoom.setReservation(reservationDao.getLastInsertedReservation(connection));
            reservationRoomDao.addReservationRoom(reservationRoom, connection);
            reservations = reservationDao.getAllReservations(connection);
        } catch (DAOException e) {
            throw new ServiceException(e);
        } finally {
            closeConnection(connection);
        }
        return reservations;
    }

    public void delete(Reservation reservation) throws ServiceException {
        Connection connection = null;
        try {
            connection = getConnection();
            reservationDao.removeReservation(reservation, connection);
        } catch (DAOException e) {
            throw new ServiceException(e);
        } finally {
            closeConnection(connection);
        }
    }

    public void update(Reservation entity) throws ServiceException {
        Connection connection = null;
        try {
            connection = getConnection();
            reservationDao.updateReservation(entity, connection);
        } catch (DAOException e) {
            throw new ServiceException(e);
        } finally {
            closeConnection(connection);
        }
    }

    public Reservation build(Map<String, String[]> params) throws ServiceException {
        ValidatorReservation validatorReservation = new ValidatorReservation();
        try {
            if (validatorReservation.validate(params)) {
                Reservation reservation = new ReservationBuilder().id(Integer.parseInt(params.get("id")[0]))
                        .dateIn(new Date(new SimpleDateFormat("yyyy-MM-dd").parse(params.get("dateIn")[0]).getTime()))
                        .dateOut(new Date(new SimpleDateFormat("yyyy-MM-dd").parse(params.get("dateOut")[0]).getTime()))
                        .costAdditionalServices(Integer.parseInt(params.get("costAdditionalServices")[0]))
                        .user(new UserBuilder().id(Integer.parseInt(params.get("idUser")[0])).build())
                        .discount(new DiscountBuilder().id(Integer.parseInt(params.get("idDiscount")[0])).build())
                        .build();
                if(params.containsKey("idRoom")){
                    reservationRoom = new ReservationRoomBuilder()
                            .room(new RoomBuilder().id(Integer.parseInt(params.get("idRoom")[0])).build())
                            .reservation(reservation)
                            .build();
                }
                return reservation;
            }
        } catch (ParseException | IncorrectDateException | IncorrectCostException e) {
            throw new ServiceException(e);
        }
        return null;
    }

    @Override
    public Reservation getLastInsertedEntity() throws ServiceException {
        Connection connection = null;
        try {
            connection = getConnection();
            return reservationDao.getLastInsertedReservation(connection);
        } catch (DAOException e) {
            throw new ServiceException(e);
        } finally {
            closeConnection(connection);
        }
    }
}