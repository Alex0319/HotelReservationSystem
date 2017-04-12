package com.netcracker.dao.daoimpl;

import com.netcracker.dao.AbstractDao;
import com.netcracker.dao.ReservationDao;
import com.netcracker.dao.bean.Reservation;
import com.netcracker.dao.bean.User;
import com.netcracker.dao.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.netcracker.dao.constant.Constants.*;

public class ReservationDaoImpl extends AbstractDao implements ReservationDao {

    private ReservationDaoImpl(){}

    public static class Holder{
        private final static ReservationDaoImpl INSTANCE = new ReservationDaoImpl();
    }

    public static ReservationDaoImpl getInstance(){
        return Holder.INSTANCE;
    }

    public List<Reservation> getAllReservations() throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Reservation> reservations = new ArrayList<Reservation>();
        try {
            connection = getConnection();
            statement = connection.prepareStatement(GET_ALL_RESERVATIONS);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Reservation reservation = new Reservation();
                User user = new User();
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setMobilePhone(resultSet.getString("mobilePhone"));
                user.setPassportNumber(resultSet.getString("passportNumber"));
                reservation.setUser(user);
                reservation.setRoomNumber(resultSet.getInt("room_number"));
                reservation.setDateIn(resultSet.getDate("date-in"));
                reservation.setDateOut(resultSet.getDate("date-out"));
                reservation.setDaysCount(resultSet.getInt("days_count"));

                reservations.add(reservation);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeConnection(connection,statement,resultSet);
        }
        return reservations;
    }

    public void addReservation(Reservation reservation) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement(ADD_RESERVATION);
            statement = fillStatement(statement, reservation);
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeConnection(connection,statement,null);
        }
    }

    public void removeReservation(Reservation reservation) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();
            statement.setInt(1, reservation.getId());
            statement = connection.prepareStatement(REMOVE_RESERVATION);
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeConnection(connection,statement,null);
        }
    }

    public void updateReservation(Reservation reservation) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement(UPDATE_RESERVATION);
            statement = fillStatement(statement, reservation);
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeConnection(connection,statement,null);
        }
    }

    private PreparedStatement fillStatement(PreparedStatement statement, Reservation reservation) throws SQLException {
        statement.setInt(1, reservation.getUser().getId());
        statement.setInt(2, reservation.getRoomNumber());
        statement.setDate(3, reservation.getDateIn());
        statement.setDate(4, reservation.getDateOut());
        statement.setInt(5, reservation.getDaysCount());
        return statement;
    }
}
