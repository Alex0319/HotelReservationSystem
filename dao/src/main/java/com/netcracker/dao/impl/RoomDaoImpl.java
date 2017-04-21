package com.netcracker.dao.impl;


import com.netcracker.dao.AbstractDao;
import com.netcracker.dao.RoomDao;
import com.netcracker.dao.bean.Room;
import com.netcracker.dao.builder.RoomBuilder;
import com.netcracker.dao.builder.RoomTypeBuilder;
import com.netcracker.dao.exception.DaoException;
import com.netcracker.dao.util.ErrorStringBuilder;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.netcracker.dao.constant.Constants.*;


public class RoomDaoImpl extends AbstractDao implements RoomDao {
    public List<String> getRoomHeaders(Connection connection) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<String> headers = new ArrayList<String>();
        StringBuilder stringBuilder = new StringBuilder();
        try {
            statement = connection.prepareStatement(GET_ALL_ROOMS_HEADERS);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                stringBuilder.append(resultSet.getInt("id")+" ");
                stringBuilder.append(resultSet.getString("name"));
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

    public List<Room> getRooms(Connection connection) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Room> rooms = new ArrayList<Room>();
        RoomBuilder roomBuilder = new RoomBuilder();
        RoomTypeBuilder roomTypeBuilder  = new RoomTypeBuilder();
        try {
            statement = connection.prepareStatement(GET_ALL_ROOMS);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                rooms.add(roomBuilder.id(resultSet.getInt("id"))
                            .roomType(roomTypeBuilder.id(resultSet.getInt("id_room_type"))
                                    .roomsCount(resultSet.getInt("rooms_count"))
                                    .bedsCount(resultSet.getInt("beds_count"))
                                    .costPerDay(resultSet.getInt("cost_per_day"))
                                    .additionalInfo(resultSet.getString("additional_info"))
                                    .bathroomsCount(resultSet.getInt("bathrooms_count"))
                                    .size(resultSet.getInt("size")).build())
                            .floor(resultSet.getInt("floor"))
                            .phone(resultSet.getString("phone"))
                            .name(resultSet.getString("name"))
                            .build());
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(statement, resultSet);
        }
        return rooms;
    }

    public void addRoom(Room room,Connection connection) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(ADD_ROOM);
            statement = fillStatement(statement, room);
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(statement, null);
        }
    }

    public void removeRoom(Room room,Connection connection) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(REMOVE_ROOM);
            statement.setInt(1, room.getId());
            statement.execute();
        }catch (SQLIntegrityConstraintViolationException e){
            throw new DaoException(buildMessage(room, e.getMessage()) ,e);
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(statement, null);
        }
    }

    public void updateRoom(Room room,Connection connection) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(UPDATE_ROOM);
            statement = fillStatement(statement, room);
            statement.setInt(5, room.getId());
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(statement, null);
        }
    }

    private PreparedStatement fillStatement(PreparedStatement statement, Room room) throws SQLException {
        statement.setInt(1, room.getRoomType().getId());
        statement.setString(2, room.getName());
        statement.setInt(3, room.getFloor());
        statement.setString(4, room.getPhone());
        return statement;
    }

    private String buildMessage(Room room, String errorMessage){
        Map<String,String> idNames = new HashMap<String, String>();
        idNames.put("id",Integer.toString(room.getId()));
        return ErrorStringBuilder.buildErrorString(idNames,errorMessage);
    }
}
