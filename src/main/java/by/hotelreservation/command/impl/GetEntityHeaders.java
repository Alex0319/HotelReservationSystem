package by.hotelreservation.command.impl;

import by.hotelreservation.command.exception.CommandException;
import by.hotelreservation.service.CrudService;
import by.hotelreservation.service.CrudServiceExtended;
import by.hotelreservation.service.CrudServiceMapper;
import by.hotelreservation.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GetEntityHeaders {
    public Object execute(HttpServletRequest req, HttpServletResponse response) throws CommandException {
        Map<String,List<String>> resultMap = new LinkedHashMap<String, List<String>>();
        Map<String, String[]> requestParams = req.getParameterMap();
        int tablesCount = requestParams.get("tableName").length;
        try {
            for (int i = 0; i < tablesCount; i++){
                CrudService service =  CrudServiceMapper.getService(requestParams.get("tableName")[i]);
                resultMap.put(requestParams.get("tableName")[i], ((CrudServiceExtended)service).getAllHeaders());
            }
        }catch (ServiceException e){
            throw new CommandException(e);
        }
        return resultMap;
    }
}
