package by.hotelreservation.service.impl;

import by.hotelreservation.bean.entity.User;
import by.hotelreservation.exception.ServiceException;
import by.hotelreservation.service.AuthService;
import by.hotelreservation.service.CrudService;
import by.hotelreservation.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class RegistrationServiceImpl implements RegistrationService {
    @Autowired
    private AuthService authService;

    @Autowired
    private CrudService<User> userService;

    @Override
    public User registration(Map<String, String[]> params) throws ServiceException {
        User user = userService.build(params);
        if(authService.checkUser(user.getEmail(),user.getPassword()) == null){
            List<User> list = userService.add(user);
            return list.get(list.size()-1);
        }
        return null;
    }
}
