package com.netcracker.web.commands.commandfactory.commandfactoriesimpl;

import com.netcracker.web.commands.Command;
import com.netcracker.web.commands.commandfactory.CommandFactory;
import com.netcracker.web.commands.commandimpl.ApplyCommand;

public class ApplyCommandFactory implements CommandFactory{
    public Command createCommand() {
        return new ApplyCommand();
    }
}
