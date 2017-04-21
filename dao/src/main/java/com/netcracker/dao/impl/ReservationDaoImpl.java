package com.netcracker.dao.impl;


import com.netcracker.dao.AbstractDao;
import com.netcracker.dao.ReservationDao;
import com.netcracker.dao.bean.Reservation;
import com.netcracker.dao.builder.DiscountBuilder;
import com.netcracker.dao.builder.ReservationBuilder;
import com.netcracker.dao.builder.UserBuilder;
import com.netcracker.dao.exception.DaoException;
import com.netcracker.dao.util.ErrorStringBuilder;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.netcracker.dao.constant.Constants.*;

public class ReservationDaoImpl extends AbstractDao implements ReservationDao {
    public List<String> getReservationHeaders(Connection connection) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<String> headers = new ArrayList<String>();
        StringBuilder stringBuilder = new StringBuilder();
        try {
            statement = connection.prepareStatement(GET_ALL_RESERVATIONS_HEADERS);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                stringBuilder.append(resultSet.getInt("id")+" ");
                stringBuilder.append(resultSet.getString("date-in")+" ");
                stringBuilder.append(resultSet.getString("date-out"));
                headers.add(stringBuilder.toString());
                stringBuilder.setLength(0);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(statement, resultSet);
        }
        return headers;
    }

    public List<Reservation> getAllReservations(Connection connection) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Reservation> reservations = new ArrayList<Reservation>();
        UserBuilder userBuilder = new UserBuilder();
        DiscountBuilder discountBuilder = new DiscountBuilder();
        ReservationBuilder reservationBuilder = new ReservationBuilder();
        try {
            statement = connection.prepareStatement(GET_ALL_RESERVATIONS);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                reservations.add(reservationBuilder.id(resultSet.getInt("id"))
                                    .dateIn(resultSet.getDate("date-in"))
                                    .dateOut(resultSet.getDate("date-out"))
                                    .user(userBuilder.id(resultSet.getInt("id_user"))
                                            .passportNumber(resultSet.getString("passport_number"))
                                            .name(resultSet.getString("name"))
                                            .surname(resultSet.getString("surname"))
                                            .sex(resultSet.getString("sex"))
                                            .mobilePhone(resultSet.getString("mobile_phone"))
                                            .build())
                                    .costAdditionalServices(resultSet.getInt("cost_additional_services"))
                                    .discount(discountBuilder.id(resultSet.getInt("discount_id"))
                                            .name(resultSet.getString("discount_name"))
                                            .build())
                                    .build());
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(statement, resultSet);
        }
        return reservations;
    }

    public void addReservation(Reservation reservation,Connection connection) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(ADD_RESERVATION);
            statement = fillStatement(statement, reservation);
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(statement, null);
        }
    }

    public void removeReservation(Reservation reservation,Connection connection) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(REMOVE_RESERVATION);
            statement.setInt(1, reservation.getId());
            statement.execute();
        }
        catch (SQLIntegrityConstraintViolationException e){
            throw new DaoException(buildMessage(reservation, e.getMessage()),e);
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(statement, null);
        }
    }

    public void updateReservation(Reservation reservation,Connection connection) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(UPDATE_RESERVATION);
            statement = fillStatement(statement, reservation);
            statement.setInt(6, reservation.getId());
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(statement, null);
        }
    }

    public Reservation getReservation(Integer id,Connection connection) throws DaoException {
        return null;
    }

    private PreparedStatement fillStatement(PreparedStatement statement, Reservation reservation) throws SQLException {
        statement.setInt(1, reservation.getUser().getId());
        statement.setDate(2, reservation.getDateIn());
        statement.setDate(3, reservation.getDateOut());
        statement.setInt(4, reservation.getCostAdditionalServices());
        statement.setInt(5,reservation.getDiscount().getId());
        return statement;
    }

    private String buildMessage(Reservation reservation, String errorMessage){
        Map<String,String> idNames = new HashMap<String, String>();
        idNames.put("id",Integer.toString(reservation.getId()));
        return ErrorStringBuilder.buildErrorString(idNames,errorMessage);
    }
}
