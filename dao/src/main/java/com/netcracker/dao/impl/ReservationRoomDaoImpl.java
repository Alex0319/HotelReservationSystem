package com.netcracker.dao.impl;


import com.netcracker.dao.AbstractDao;
import com.netcracker.dao.ReservationRoomDao;
import com.netcracker.dao.bean.Reservation;
import com.netcracker.dao.bean.ReservationRoom;
import com.netcracker.dao.bean.Room;
import com.netcracker.dao.builder.*;
import com.netcracker.dao.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.netcracker.dao.constant.Constants.*;

public class ReservationRoomDaoImpl extends AbstractDao implements ReservationRoomDao {
    public List<ReservationRoom> getReservationRooms(Connection connection) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<ReservationRoom> reservationRooms = new ArrayList<ReservationRoom>();
        RoomBuilder roomBuilder = new RoomBuilder();
        UserBuilder userBuilder = new UserBuilder();
        RoomTypeBuilder roomTypeBuilder  = new RoomTypeBuilder();
        DiscountBuilder discountBuilder = new DiscountBuilder();
        ReservationBuilder reservationBuilder = new ReservationBuilder();
        ReservationRoomBuilder reservationRoomBuilder = new ReservationRoomBuilder();
        try {
            statement = connection.prepareStatement(GET_ALL_RESERVATION_ROOMS);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Reservation reservation = reservationBuilder.id(resultSet.getInt("id_reservation"))
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
                                .build();
                Room room = roomBuilder.id(resultSet.getInt("id_room"))
                                .roomType(roomTypeBuilder.id(resultSet.getInt("id_room_type"))
                                        .roomsCount(resultSet.getInt("rooms_count"))
                                        .bedsCount(resultSet.getInt("beds_count"))
                                        .costPerDay(resultSet.getInt("cost_per_day"))
                                        .additionalInfo(resultSet.getString("additional_info"))
                                        .build())
                                .floor(resultSet.getInt("floor"))
                                .phone(resultSet.getString("phone"))
                                .build();

                reservationRooms.add(reservationRoomBuilder.reservation(reservation)
                                        .room(room)
                                        .build());
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(statement, resultSet);
        }
        return reservationRooms;
    }

    public void addReservationRoom(ReservationRoom reservationRoom,Connection connection) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(ADD_RESERVATION_ROOM);
            statement = fillStatement(statement, reservationRoom);
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(statement, null);
        }
    }

    public void removeReservationRoom(ReservationRoom reservationRoom,Connection connection) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(REMOVE_RESERVATION_ROOM);
            statement = fillStatement(statement, reservationRoom);
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(statement, null);
        }
    }

    public void updateReservationRoom(ReservationRoom reservationRoom,Connection connection) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(UPDATE_RESERVATION_ROOM);
            statement = fillStatement(statement, reservationRoom);
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(statement, null);
        }
    }

    private PreparedStatement fillStatement(PreparedStatement statement, ReservationRoom reservationRoom) throws SQLException {
        statement.setInt(1, reservationRoom.getRoom().getId());
        statement.setInt(2, reservationRoom.getReservation().getId());

        return statement;
    }
}
