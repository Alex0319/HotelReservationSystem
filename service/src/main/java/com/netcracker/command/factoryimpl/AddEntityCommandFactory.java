package com.netcracker.command.factoryimpl;

import com.netcracker.command.Command;
import com.netcracker.command.CommandFactory;
import com.netcracker.command.impl.AddEntity;

public class AddEntityCommandFactory implements CommandFactory {
    public Command createCommand() {
        return new AddEntity();
    }
}
