package by.hotelreservation.command.impl;

import by.hotelreservation.command.exception.CommandException;
import by.hotelreservation.service.TablesInfoService;
import by.hotelreservation.service.exception.ServiceException;
import by.hotelreservation.service.impl.TablesInfoServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GetTableNames  {
    public Object execute(HttpServletRequest req, HttpServletResponse response) throws CommandException {
        List<String> resultList;
        try {

            TablesInfoService service = new TablesInfoServiceImpl();
            resultList = service.getAllTablesNames();
        }catch (ServiceException e){
            throw new CommandException(e);
        }
        return resultList;
    }
}

