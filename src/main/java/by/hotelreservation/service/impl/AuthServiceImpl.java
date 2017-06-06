package by.hotelreservation.service.impl;

import by.hotelreservation.bean.entity.User;
import by.hotelreservation.exception.DAOException;
import by.hotelreservation.exception.ServiceException;
import by.hotelreservation.newdao.UserDao;
import by.hotelreservation.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
	@Autowired
	private UserDao authDao;

	public User checkUser(String email, String password)  throws ServiceException {
		try {
			return authDao.checkUser(email,password);
		}catch (DAOException e){
			throw new ServiceException(e);
		}
	}

	public boolean logout(){
		return false;
	}

}