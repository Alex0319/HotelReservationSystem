package by.hotelreservation.service;

import by.hotelreservation.bean.entity.User;
import by.hotelreservation.exception.ServiceException;

import java.util.Map;

public interface RegistrationService {
    User registration(Map<String, String[]> params)  throws ServiceException;
}
