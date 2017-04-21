package com.netcracker.command.factoryimpl;

import com.netcracker.command.Command;
import com.netcracker.command.CommandFactory;
import com.netcracker.command.impl.GetEntityHeaders;

public class GetAllHeadersCommandFactory implements CommandFactory {
    public Command createCommand() {
        return new GetEntityHeaders();
    }
}
