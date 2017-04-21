package com.netcracker.command.impl;

import com.netcracker.command.Command;
import com.netcracker.command.exception.CommandException;
import com.netcracker.service.CrudService;
import com.netcracker.service.ServiceMapper;
import com.netcracker.service.exception.ServiceException;

import java.util.Map;

public class UpdateEntity implements Command {
    public Object execute(Map<String, String[]> requestParameters) throws CommandException {
        try {
            CrudService service =  ServiceMapper.getService(requestParameters.get("tableName")[0]);
            service.updateEntity(service.buildEntity(requestParameters));
        }catch (ServiceException e){
            throw new CommandException(e);
        }
        return null;
    }
}
