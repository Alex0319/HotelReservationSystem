package by.hotelreservation.service;

import by.hotelreservation.bean.User;
import by.hotelreservation.service.exception.ServiceException;

/**
 * Created by 1 on 26.04.2017.
 */
public interface RegistrationService {
    User registration(User user)  throws ServiceException;
}
