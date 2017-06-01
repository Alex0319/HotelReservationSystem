package by.hotelreservation.service;


import by.hotelreservation.bean.entity.User;
import by.hotelreservation.exception.ServiceException;

public interface AuthService {
	User checkUser(String login, String password)  throws ServiceException;
}