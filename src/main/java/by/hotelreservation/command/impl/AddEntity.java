package by.hotelreservation.command.impl;

import by.hotelreservation.command.exception.CommandException;
import by.hotelreservation.service.CrudService;
import by.hotelreservation.service.CrudServiceMapper;
import by.hotelreservation.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class AddEntity   {
    public Object execute(HttpServletRequest req, HttpServletResponse response) throws CommandException {
        Object result;
        try {
            Map<String, String[]> requestParams = req.getParameterMap();
            CrudService service =  CrudServiceMapper.getService(requestParams.get("tableName")[0]);
            result = service.addEntity(service.buildEntity(requestParams));
        }catch (ServiceException e){
            throw new CommandException(e);
        }
        return result;
    }
}
