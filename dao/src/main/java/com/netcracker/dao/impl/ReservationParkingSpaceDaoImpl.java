package com.netcracker.dao.impl;


import com.netcracker.dao.AbstractDao;
import com.netcracker.dao.ReservationParkingSpaceDao;
import com.netcracker.dao.bean.ReservationParkingSpace;
import com.netcracker.dao.builder.*;
import com.netcracker.dao.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.netcracker.dao.constant.Constants.*;

public class ReservationParkingSpaceDaoImpl extends AbstractDao implements ReservationParkingSpaceDao {
    public List<ReservationParkingSpace> getReservationParkingSpaces(Connection connection) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<ReservationParkingSpace> reservationParkingSpaces = new ArrayList<ReservationParkingSpace>();
        UserBuilder userBuilder = new UserBuilder();
        DiscountBuilder discountBuilder = new DiscountBuilder();
        ReservationBuilder reservationBuilder = new ReservationBuilder();
        ParkingSpaceBuilder parkingSpaceBuilder = new ParkingSpaceBuilder();
        ReservationParkingSpaceBuilder reservationParkingSpaceBuilder = new ReservationParkingSpaceBuilder();
        try {
            statement = connection.prepareStatement(GET_ALL_RESERVATION_PARKING_SPACES);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                reservationParkingSpaces.add(reservationParkingSpaceBuilder
                                    .reservation(reservationBuilder.id(resultSet.getInt("id_reservation"))
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
                                        .build())
                                    .parkingSpace(parkingSpaceBuilder.id(resultSet.getInt("id_parking_space"))
                                        .level(resultSet.getInt("level"))
                                        .reserved(resultSet.getByte("is_reserved"))
                                        .build())
                                    .build());
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(statement, resultSet);
        }
        return reservationParkingSpaces;
    }

    public void addReservationParkingSpace(ReservationParkingSpace reservationParkingSpace, Connection connection) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(ADD_RESERVATION_PARKING_SPACE);
            statement = fillStatement(statement, reservationParkingSpace);
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(statement, null);
        }
    }

    public void removeReservationParkingSpace(ReservationParkingSpace reservationParkingSpace,Connection connection) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(REMOVE_RESERVATION_PARKING_SPACE);
            statement = fillStatement(statement, reservationParkingSpace);
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(statement, null);
        }
    }

    public void updateReservationParkingSpace(ReservationParkingSpace reservationParkingSpace,Connection connection) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(UPDATE_RESERVATION_PARKING_SPACE);
            statement = fillStatement(statement, reservationParkingSpace);
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(statement, null);
        }
    }
    private PreparedStatement fillStatement(PreparedStatement statement, ReservationParkingSpace reservationParkingSpace) throws SQLException {
        statement.setInt(1, reservationParkingSpace.getParkingSpace().getId());
        statement.setInt(2, reservationParkingSpace.getReservation().getId());
        return statement;
    }
}
