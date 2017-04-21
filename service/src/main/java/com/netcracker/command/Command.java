package com.netcracker.command;

import com.netcracker.command.exception.CommandException;

import java.util.Map;

public interface Command {
    Object execute(Map<String, String[]> reqParams) throws CommandException;
}
