package com.netcracker.command.impl;

import com.netcracker.command.Command;
import com.netcracker.command.exception.CommandException;
import com.netcracker.service.CrudService;
import com.netcracker.service.ServiceMapper;
import com.netcracker.service.exception.ServiceException;

import java.util.List;
import java.util.Map;

public class GetAllEntities implements Command {
    public Object execute(Map<String, String[]> requestParameters) throws CommandException {
        List<?> resultList;
        try {
            CrudService service =  ServiceMapper.getService(requestParameters.get("tableName")[0]);
            resultList = service.getAllEntities();
        }catch (ServiceException e){
            throw new CommandException(e);
        }
        return resultList;
    }
}
