package by.hotelreservation.controller;

import by.hotelreservation.bean.entity.User;
import by.hotelreservation.exception.ServiceException;
import by.hotelreservation.security.MD5;
import by.hotelreservation.service.AuthService;
import by.hotelreservation.service.impl.AuthServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class Authorization  {
    private static final Logger logger = LogManager.getLogger(Authorization.class);

    @ResponseBody
    @RequestMapping(value = "/authorization", method = RequestMethod.POST, produces = "application/json")
    public Object execute(HttpServletRequest request){
        User user = null;
        try {
            Map<String, String[]> requestParams = request.getParameterMap();
            AuthService service = new AuthServiceImpl();
            user = service.checkUser(requestParams.get("email")[0], MD5.crypt(requestParams.get("password")[0]));
        } catch (ServiceException e) {
            logger.error(e);
        }
        return user;
    }
}
