package by.hotelreservation.service;

import by.hotelreservation.bean.entity.User;
import by.hotelreservation.exception.ServiceException;

public interface RegistrationService {
    User registration(User user)  throws ServiceException;
}
