package by.hotelreservation.command.impl;

import by.hotelreservation.bean.User;
import by.hotelreservation.command.exception.CommandException;
import by.hotelreservation.security.MD5;
import by.hotelreservation.service.AuthService;
import by.hotelreservation.service.exception.ServiceException;
import by.hotelreservation.service.impl.AuthServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class Authorization  {
    public Object execute(HttpServletRequest req, HttpServletResponse response) throws CommandException {
        Map<String, String[]> requestParams = req.getParameterMap();
        try {
            User user;
            AuthService service = new AuthServiceImpl();
            user = service.checkUser(requestParams.get("email")[0], MD5.crypt(requestParams.get("password")[0]));
            return user;
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}
