package by.hotelreservation.service;


import by.hotelreservation.bean.User;
import by.hotelreservation.service.exception.ServiceException;

public interface AuthService {

	User checkUser(String login, String password)  throws ServiceException;
}