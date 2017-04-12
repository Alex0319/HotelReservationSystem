package com.netcracker.web.command;

import com.netcracker.web.command.exception.CommandException;

public interface Command {
    Object execute(String request) throws CommandException;
}
