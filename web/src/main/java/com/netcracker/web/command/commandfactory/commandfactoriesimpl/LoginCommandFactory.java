package com.netcracker.web.command.commandfactory.commandfactoriesimpl;

import com.netcracker.web.command.Command;
import com.netcracker.web.command.commandfactory.CommandFactory;
import com.netcracker.web.command.commandimpl.LoginCommand;

public class LoginCommandFactory implements CommandFactory {
    public Command createCommand() {
        return new LoginCommand();
    }
}
