package com.netcracker.dao.daoimpl;

import com.netcracker.dao.AbstractDao;
import com.netcracker.dao.RoomDao;
import com.netcracker.dao.bean.Room;
import com.netcracker.dao.bean.RoomType;
import com.netcracker.dao.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.netcracker.dao.constant.Constants.*;

public class RoomDaoImpl extends AbstractDao implements RoomDao {

    private RoomDaoImpl(){}

    public static class Holder{
        private final static RoomDaoImpl INSTANCE = new RoomDaoImpl();
    }

    public static RoomDaoImpl getInstance(){
        return RoomDaoImpl.Holder.INSTANCE;
    }

    public List<Room> getRooms() throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Room> rooms = new ArrayList<Room>();
        try {
            connection = getConnection();
            statement = connection.prepareStatement(GET_ALL_ROOMS);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Room room = new Room();
                RoomType roomType = new RoomType(resultSet.getInt("room_type.id"),
                        resultSet.getInt("rooms_count"),
                        resultSet.getInt("beds_count"),
                        resultSet.getInt("cost_per_day"),
                        resultSet.getString("additional_info"));
                room.setId(resultSet.getInt("id"));
                room.setRoomType(roomType);
                room.setFloor(resultSet.getInt("floor"));
                room.setPhone(resultSet.getString("phone"));
                rooms.add(room);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeConnection(connection,statement,null);
        }
        return rooms;
    }

    public void addRoom(Room room) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = getConnection();
            statement = connection.prepareStatement(ADD_ROOM);
            statement = fillStatement(statement, room);
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeConnection(connection,statement,null);
        }
    }

    public void removeRoom(Room room) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();
            statement.setInt(1, room.getId());
            statement = connection.prepareStatement(REMOVE_ROOM);
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeConnection(connection,statement,null);
        }
    }

    public void updateRoom(Room room) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement(UPDATE_ROOM);
            statement = fillStatement(statement, room);
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeConnection(connection,statement,null);
        }
    }

    private PreparedStatement fillStatement(PreparedStatement statement, Room room) throws SQLException {
        statement.setInt(1, room.getRoomType().getId());
        statement.setInt(2, room.getFloor());
        statement.setString(3, room.getPhone());
        return statement;
    }
}
