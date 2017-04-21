package com.netcracker.service.impl;

import com.netcracker.dao.RoomDao;
import com.netcracker.dao.bean.Room;
import com.netcracker.dao.builder.RoomBuilder;
import com.netcracker.dao.builder.RoomTypeBuilder;
import com.netcracker.dao.exception.DaoException;
import com.netcracker.dao.impl.RoomDaoImpl;
import com.netcracker.service.AbstractService;
import com.netcracker.service.CrudServiceExtended;
import com.netcracker.service.exception.ServiceException;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public class RoomServiceImpl extends AbstractService implements CrudServiceExtended<Room> {
	private RoomDao roomDao = new RoomDaoImpl();

	public List<String> getAllHeaders() throws ServiceException {
		Connection connection = null;
		try {
			connection = getConnection();
			return roomDao.getRoomHeaders(connection);
		}catch (DaoException e){
			throw new ServiceException(e);
		}finally {
			closeConnection(connection);
		}
	}

	public List<Room> getAllEntities() throws ServiceException {
		Connection connection = null;
		try {
			connection = getConnection();
			return roomDao.getRooms(connection);
		}catch (DaoException e){
			throw new ServiceException(e);
		}finally {
			closeConnection(connection);
		}
	}

	public List<Room> addEntity(Room entity) throws ServiceException {
		List<Room> rooms;
		Connection connection = null;
		try {
			connection = getConnection();
			roomDao.addRoom(entity,connection);
			rooms = roomDao.getRooms(connection);
		}catch (DaoException e){
			throw new ServiceException(e);
		}finally {
			closeConnection(connection);
		}
		return rooms;
	}

	public void removeEntity(Room room) throws ServiceException {
		Connection connection = null;
		try {
			connection = getConnection();
			roomDao.removeRoom(room,connection);
		}catch (DaoException e){
			throw new ServiceException(e);
		}finally {
			closeConnection(connection);
		}
	}

	public void updateEntity(Room entity) throws ServiceException {
		Connection connection = null;
		try {
			connection = getConnection();
			roomDao.updateRoom(entity,connection);
		}catch (DaoException e){
			throw new ServiceException(e);
		}finally {
			closeConnection(connection);
		}
	}

    public Room buildEntity(Map<String, String[]> params) throws ServiceException {
		return new RoomBuilder().id(Integer.parseInt(params.get("id")[0]))
				.roomType(new RoomTypeBuilder().id(Integer.parseInt(params.get("id_roomType")[0]))
						.build())
				.name(params.get("name")[0])
				.floor(Integer.parseInt(params.get("floor")[0]))
				.phone(params.get("phone")[0])
				.build();
	}
}