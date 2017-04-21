package com.netcracker.command.factoryimpl;

import com.netcracker.command.Command;
import com.netcracker.command.CommandFactory;
import com.netcracker.command.impl.GetTableNames;

public class GetTablesNamesCommandFactory implements CommandFactory {
    public Command createCommand() {
        return new GetTableNames();
    }
}
