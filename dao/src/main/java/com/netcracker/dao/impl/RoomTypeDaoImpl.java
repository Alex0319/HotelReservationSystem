package com.netcracker.dao.impl;


import com.netcracker.dao.AbstractDao;
import com.netcracker.dao.RoomTypeDao;
import com.netcracker.dao.bean.RoomType;
import com.netcracker.dao.builder.RoomTypeBuilder;
import com.netcracker.dao.exception.DaoException;
import com.netcracker.dao.util.ErrorStringBuilder;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.netcracker.dao.constant.Constants.*;

public class RoomTypeDaoImpl extends AbstractDao implements RoomTypeDao {
    public List<String> getRoomTypeHeaders(Connection connection) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<String> headers = new ArrayList<String>();
        StringBuilder stringBuilder = new StringBuilder();
        try {
            statement = connection.prepareStatement(GET_ALL_ROOM_TYPES_HEADERS);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                stringBuilder.append(resultSet.getInt("id")+" rooms count: ");
                stringBuilder.append(resultSet.getString("rooms_count"));
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

    public List<RoomType> getRoomTypes(Connection connection) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<RoomType> roomTypes = new ArrayList<RoomType>();
        RoomTypeBuilder roomTypeBuilder  = new RoomTypeBuilder();
        try {
            statement = connection.prepareStatement(GET_ALL_ROOM_TYPES);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                 roomTypes.add(roomTypeBuilder.id(resultSet.getInt("id"))
                                .roomsCount(resultSet.getInt("rooms_count"))
                                .bedsCount(resultSet.getInt("beds_count"))
                                .costPerDay(resultSet.getInt("cost_per_day"))
                                .additionalInfo(resultSet.getString("additional_info"))
                                .bathroomsCount(resultSet.getInt("bathrooms_count"))
                                .size(resultSet.getInt("size"))
                                .build());
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(statement, resultSet);
        }
        return roomTypes;
    }

    public void addRoomType(RoomType roomType,Connection connection) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(ADD_ROOM_TYPE);
            statement = fillStatement(statement, roomType);
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(statement, null);
        }
    }

    public void removeRoomType(RoomType roomType,Connection connection) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(REMOVE_ROOM_TYPE);
            statement.setInt(1, roomType.getId());
            statement.execute();
        }catch (SQLIntegrityConstraintViolationException e){
            throw new DaoException(buildMessage(roomType, e.getMessage()) ,e);
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(statement, null);
        }
    }

    public void updateRoomType(RoomType roomType,Connection connection) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(UPDATE_ROOM_TYPE);
            statement = fillStatement(statement, roomType);
            statement.setInt(7, roomType.getId());
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(statement, null);
        }
    }

    private PreparedStatement fillStatement(PreparedStatement statement, RoomType roomType) throws SQLException {
        statement.setInt(1, roomType.getRoomsCount());
        statement.setInt(2, roomType.getBedsCount());
        statement.setFloat(3, roomType.getCostPerDay());
        statement.setString(4, roomType.getAdditionalInfo());
        statement.setInt(5, roomType.getBathroomsCount());
        statement.setInt(6, roomType.getSize());
        return statement;
    }

    private String buildMessage(RoomType roomType, String errorMessage){
        Map<String,String> idNames = new HashMap<String, String>();
        idNames.put("id",Integer.toString(roomType.getId()));
        return ErrorStringBuilder.buildErrorString(idNames,errorMessage);
    }
}
