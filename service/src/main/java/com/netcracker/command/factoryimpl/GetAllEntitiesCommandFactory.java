package com.netcracker.command.factoryimpl;

import com.netcracker.command.Command;
import com.netcracker.command.CommandFactory;
import com.netcracker.command.impl.GetAllEntities;

public class GetAllEntitiesCommandFactory implements CommandFactory {
    public Command createCommand() {
        return new GetAllEntities();
    }
}
