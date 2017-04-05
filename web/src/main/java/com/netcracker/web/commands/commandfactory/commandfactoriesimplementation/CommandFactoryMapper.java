package com.netcracker.web.commands.commandfactory.commandfactoriesimplementation;

import com.netcracker.web.commands.commandfactory.CommandFactory;

import java.util.HashMap;
import java.util.Map;

public final class CommandFactoryMapper {
    final private static Map<String, CommandFactory> commands = new HashMap();

    static {
    }

    public static CommandFactory getCommandFactory(String commandName) {
        return commands.get(commandName);
    }
}