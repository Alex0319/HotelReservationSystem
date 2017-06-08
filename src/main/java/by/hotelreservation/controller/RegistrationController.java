package by.hotelreservation.controller;

import by.hotelreservation.bean.entity.User;
import by.hotelreservation.exception.ServiceException;
import by.hotelreservation.service.RegistrationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping(value = "/registration")
public class RegistrationController {
    private static final Logger logger = LogManager.getLogger(RegistrationController.class);

    @Autowired
    private RegistrationService registrationService;

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public Object execute(HttpServletRequest req){
        Map<String, String[]> requestParams = req.getParameterMap();
        User user = null;
        try {
            user = registrationService.registration(requestParams);
        } catch (ServiceException e) {
           logger.error(e);
        }
        return user;
    }
}
