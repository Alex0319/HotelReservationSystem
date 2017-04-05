package com.netcracker.web.commands;

import com.netcracker.web.commands.exception.CommandException;

public interface Command {
    Object execute(String request) throws CommandException;
}
