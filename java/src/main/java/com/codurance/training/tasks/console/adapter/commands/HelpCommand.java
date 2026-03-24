package com.codurance.training.tasks.console.adapter.commands;

import com.codurance.training.tasks.console.adapter.Console;

public class HelpCommand implements  Command {
    private  Console console;
    public HelpCommand(Console console) {
        this.console = console;
    }

    @Override
    public void execute() {
        console.println("Commands:");
        console.println("  show");
        console.println("  add project <project name>");
        console.println("  add task <project name> <task description>");
        console.println("  check <task ID>");
        console.println("  uncheck <task ID>");
        console.println("");
    }
}
