package com.netcracker.command.impl;

import com.netcracker.command.Command;
import com.netcracker.command.exception.CommandException;
import com.netcracker.service.CrudService;
import com.netcracker.service.ServiceMapper;
import com.netcracker.service.exception.ServiceException;

import java.util.Map;

public class RemoveEntity implements Command {
    public Object execute(Map<String, String[]> requestParameters) throws CommandException {
        String result = null;
        try {
            CrudService service =  ServiceMapper.getService(requestParameters.get("tableName")[0]);
            service.removeEntity(service.buildEntity(requestParameters));
        }catch (ServiceException e){
            throw new CommandException(e);
        }
        return result;
    }
}
