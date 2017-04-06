package com.netcracker.web.commands.commandfactory.commandfactoriesimpl;

import com.netcracker.web.commands.Command;
import com.netcracker.web.commands.commandfactory.CommandFactory;
import com.netcracker.web.commands.commandimpl.LoginCommand;

public class LoginCommandFactory implements CommandFactory {
    public Command createCommand() {
        return new LoginCommand();
    }
}
