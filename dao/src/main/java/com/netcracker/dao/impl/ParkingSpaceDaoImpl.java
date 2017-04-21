package com.netcracker.dao.impl;

import com.netcracker.dao.AbstractDao;
import com.netcracker.dao.ParkingSpaceDao;
import com.netcracker.dao.bean.ParkingSpace;
import com.netcracker.dao.builder.ParkingSpaceBuilder;
import com.netcracker.dao.exception.DaoException;
import com.netcracker.dao.util.ErrorStringBuilder;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.netcracker.dao.constant.Constants.*;

public class ParkingSpaceDaoImpl extends AbstractDao implements ParkingSpaceDao {
    public List<String> getParkingSpaceHeaders(Connection connection) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<String> headers = new ArrayList<String>();
        StringBuilder stringBuilder = new StringBuilder();
        try {
            statement = connection.prepareStatement(GET_ALL_PARKING_SPACES_HEADERS);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                stringBuilder.append(resultSet.getInt("id")+" ");
                stringBuilder.append("level " + resultSet.getString("level"));
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

    public List<ParkingSpace> getParkingSpaces(Connection connection) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<ParkingSpace> parkingSpaces = new ArrayList<ParkingSpace>();
        ParkingSpaceBuilder parkingSpaceBuilder = new ParkingSpaceBuilder();
        try {
            statement = connection.prepareStatement(GET_ALL_PARKING_SPACES);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                parkingSpaces.add(parkingSpaceBuilder.id(resultSet.getInt("id"))
                                    .level(resultSet.getInt("level"))
                                    .reserved(resultSet.getByte("is_reserved"))
                                    .build());
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(statement, resultSet);
        }
        return parkingSpaces;
    }

    public void addParkingSpace(ParkingSpace parkingSpace,Connection connection) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(ADD_PARKING_SPACE);
            statement = fillStatement(statement, parkingSpace);
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(statement, null);
        }
    }

    public void removeParkingSpace(ParkingSpace parkingSpace,Connection connection) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(REMOVE_PARKING_SPACE);
            statement.setInt(1, parkingSpace.getId());
            statement.execute();
        }catch (SQLIntegrityConstraintViolationException e){
            throw new DaoException(buildMessage(parkingSpace, e.getMessage()),e);
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(statement, null);
        }
    }

    public void updateParkingSpace(ParkingSpace parkingSpace,Connection connection) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(UPDATE_PARKING_SPACE);
            statement = fillStatement(statement, parkingSpace);
            statement.setInt(3, parkingSpace.getId());
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(statement, null);
        }
    }

    public ParkingSpace getParkingSpace(Integer id,Connection connection) throws DaoException {
        return null;
    }

    private PreparedStatement fillStatement(PreparedStatement statement, ParkingSpace parkingSpace) throws SQLException {
        statement.setInt(1, parkingSpace.getLevel());
        statement.setByte(2, parkingSpace.getReserved());
        return statement;
    }

    private String buildMessage(ParkingSpace parkingSpace, String errorMessage){
        Map<String,String> idNames = new HashMap<String, String>();
        idNames.put("id",Integer.toString(parkingSpace.getId()));
        return ErrorStringBuilder.buildErrorString(idNames,errorMessage);
    }
}