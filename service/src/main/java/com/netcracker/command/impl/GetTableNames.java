package com.netcracker.command.impl;

import com.netcracker.command.Command;
import com.netcracker.command.exception.CommandException;
import com.netcracker.service.TablesInfoService;
import com.netcracker.service.exception.ServiceException;
import com.netcracker.service.impl.TablesInfoServiceImpl;

import java.util.List;
import java.util.Map;

public class GetTableNames implements Command {
    public Object execute(Map<String, String[]> requestParameters) throws CommandException {
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

