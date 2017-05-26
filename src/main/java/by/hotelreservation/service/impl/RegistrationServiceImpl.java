package by.hotelreservation.service.impl;

import by.hotelreservation.bean.User;
import by.hotelreservation.service.AuthService;
import by.hotelreservation.service.CrudService;
import by.hotelreservation.service.RegistrationService;
import by.hotelreservation.service.exception.ServiceException;

import java.util.List;

public class RegistrationServiceImpl implements RegistrationService{
    AuthService authService = new AuthServiceImpl();
    CrudService crudService = new UserServiceImpl();
    @Override
    public User registration(User user) throws ServiceException {
        if(authService.checkUser(user.getEmail(),user.getPassword()) == null){
            List<User> list = crudService.addEntity(user);
            return list.get(list.size()-1);
        }
        return null;
    }
}
