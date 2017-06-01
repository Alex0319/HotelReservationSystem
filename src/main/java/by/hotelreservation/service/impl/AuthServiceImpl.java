package by.hotelreservation.service.impl;

import by.hotelreservation.bean.entity.User;
import by.hotelreservation.dao.AuthDao;
import by.hotelreservation.dao.impl.UserDaoImpl;
import by.hotelreservation.exception.DAOException;
import by.hotelreservation.exception.ServiceException;
import by.hotelreservation.service.AbstractService;
import by.hotelreservation.service.AuthService;

import java.sql.Connection;

public class AuthServiceImpl extends AbstractService implements AuthService {
	private AuthDao authDao = new UserDaoImpl();
	public User checkUser(String email, String password)  throws ServiceException {
		Connection connection = null;
		try {
			connection = getConnection();
			return authDao.authorisation(email,password,connection);
		}catch (DAOException e){
			throw new ServiceException(e);
		}finally {
			closeConnection(connection);
		}
	}

	public boolean logout(){
		return false;
	}

}