package by.hotelreservation.controller;

import by.hotelreservation.bean.entity.User;
import by.hotelreservation.exception.ServiceException;
import by.hotelreservation.service.CrudService;
import by.hotelreservation.service.RegistrationService;
import by.hotelreservation.service.impl.RegistrationServiceImpl;
import by.hotelreservation.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class Registration  {
    private static final Logger logger = LogManager.getLogger(Registration.class);

    @ResponseBody
    @RequestMapping(value = "/registration", method = RequestMethod.POST, produces = "application/json")
    public Object execute(HttpServletRequest req){
        Map<String, String[]> requestParams = req.getParameterMap();
        User user = null;
        try {
            RegistrationService registrationService = new RegistrationServiceImpl();
            CrudService<User> userService = new UserServiceImpl();
            user = registrationService.registration(userService.build(requestParams));
        } catch (ServiceException e) {
           logger.error(e);
        }
        return user;
    }
}
