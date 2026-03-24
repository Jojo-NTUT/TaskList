package com.codurance.training.tasks.console.adapter.commands;

import com.codurance.training.tasks.console.adapter.Console;

public class ErrorCommand implements Command {
    private Console console;
        private String command;

    public ErrorCommand(Console console, String command) {
        this.console = console;
        this.command = command;
    }

    @Override
    public void execute() {
        console.printf("I don't know what the command \"%s\" is.", command);
        console.println("");
    }
}
