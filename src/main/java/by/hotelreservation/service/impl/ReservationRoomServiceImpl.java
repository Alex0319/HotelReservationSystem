package by.hotelreservation.service.impl;

import by.hotelreservation.bean.entity.Reservation;
import by.hotelreservation.builder.DiscountBuilder;
import by.hotelreservation.builder.ReservationBuilder;
import by.hotelreservation.builder.UserBuilder;
import by.hotelreservation.exception.DAOException;
import by.hotelreservation.exception.ServiceException;
import by.hotelreservation.exception.validateexception.IncorrectCostException;
import by.hotelreservation.exception.validateexception.IncorrectDateException;
import by.hotelreservation.dao.ReservationDao;
import by.hotelreservation.service.CrudService;
import by.hotelreservation.service.validator.ValidatorReservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

@Service(value = "reservation_room_service")
public class ReservationRoomServiceImpl implements CrudService<Reservation> {
    @Autowired
    private ReservationDao reservationDao;

    public List<Reservation> getAll() throws ServiceException {
        try {
            return reservationDao.getAll();
        }catch (DAOException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public Reservation getById(int id) throws ServiceException {
        return null;
    }

    public List<Reservation> add(Reservation entity) throws ServiceException {
        List<Reservation> reservations;
        try {
            reservationDao.add(entity);
            reservations = reservationDao.getAll();
        }catch (DAOException e){
            throw new ServiceException(e);
        }
        return reservations;
    }

    public void delete(Reservation reservation) throws ServiceException {
        try {
            reservationDao.remove(reservation.getId());
        }catch (DAOException e){
            throw new ServiceException(e);
        }
    }

    public void update(Reservation entity) throws ServiceException {
        try {
            reservationDao.update(entity);
        }catch (DAOException e){
            throw new ServiceException(e);
        }
    }

    public List<Reservation> getReservationRoomByUser(int idUser) throws ServiceException{
        List<Reservation> reservations;
        try {
            reservations = reservationDao.getReservationByUser(idUser);
        }catch (DAOException e){
            throw new ServiceException(e);
        }
        return reservations;
    }

    public Reservation getReservationRoomByReservation(int idReservation) throws ServiceException{
        Reservation reservation;
        try {
            reservation = reservationDao.getById(idReservation);
        }catch (DAOException e){
            throw new ServiceException(e);
        }
        return reservation;
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
                return reservation;
            }
        } catch (ParseException | IncorrectDateException | IncorrectCostException e) {
            throw new ServiceException(e);
        }
        return null;
    }
}
