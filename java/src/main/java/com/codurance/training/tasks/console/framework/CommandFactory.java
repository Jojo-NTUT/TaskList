package com.codurance.training.tasks.console.framework;

import com.codurance.training.tasks.console.adapter.Console;
import com.codurance.training.tasks.console.adapter.commands.*;
import com.codurance.training.tasks.taskManagement.useCase.*;

public class CommandFactory {
    private final Console console;
    private final AddProjectUseCase addProjectUseCase;
    private final AddTaskUseCase addTaskUseCase;
    private final CheckTaskUseCase checkTaskUseCase;
    private final UncheckTaskUseCase uncheckTaskUseCase;
    private final ShowProjectsUseCase showProjectsUseCase;

    public CommandFactory(
            Console console,
            AddProjectUseCase   addProjectUseCase,
            AddTaskUseCase addTaskUseCase,
            CheckTaskUseCase    checkTaskUseCase,
            UncheckTaskUseCase uncheckTaskUseCase,
            ShowProjectsUseCase showProjectsUseCase) {
        this.console = console;
        this.addProjectUseCase   = addProjectUseCase;
        this.addTaskUseCase      = addTaskUseCase;
        this.checkTaskUseCase    = checkTaskUseCase;
        this.uncheckTaskUseCase  = uncheckTaskUseCase;
        this.showProjectsUseCase = showProjectsUseCase;
    }

    public Command create(String commandLine) {
        String[] commandRest = commandLine.split(" ", 2);
        String command = commandRest[0];
        switch (command) {
            case "show":
                return new ShowCommand(showProjectsUseCase, console);
            case "add":
                return new AddCommand(commandRest[1], addProjectUseCase, addTaskUseCase, console);
            case "check":
                return new CheckCommand(commandRest[1], true, checkTaskUseCase, uncheckTaskUseCase, console);
            case "uncheck":
                return new CheckCommand(commandRest[1], false, checkTaskUseCase, uncheckTaskUseCase, console);
            case "help":
                return new HelpCommand(console);
            default:
                return new ErrorCommand(console,command);
        }
    }
}
