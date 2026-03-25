package com.codurance.training.tasks.console.adapter.commands;

import com.codurance.training.tasks.console.adapter.Console;
import com.codurance.training.tasks.taskManagement.entities.ProjectName;
import com.codurance.training.tasks.taskManagement.useCase.AddProjectUseCase;
import com.codurance.training.tasks.taskManagement.useCase.AddTaskUseCase;

public class AddCommand implements  Command {
    private final String commandLine;
    private final AddProjectUseCase addProjectUseCase;
    private final AddTaskUseCase addTaskUseCase;
    private final Console console;

    public AddCommand(
            String commandLine,
            AddProjectUseCase addProjectUseCase,
            AddTaskUseCase addTaskUseCase,
            Console console) {
        this.commandLine = commandLine;
        this.addProjectUseCase = addProjectUseCase;
        this.addTaskUseCase = addTaskUseCase;
        this.console = console;
    }

    @Override
    public void execute() {
        String[] subcommandRest = commandLine.split(" ", 2);
        String subcommand = subcommandRest[0];

        if ("project".equals(subcommand)) {
            addProjectUseCase.addProject(subcommandRest[1]);
        } else if ("task".equals(subcommand)) {
            String[] projectTask = subcommandRest[1].split(" ", 2);
            String projectName = projectTask[0];
            String description = projectTask[1];

            if (!addTaskUseCase.addTask(projectName, description)) {
                console.printf("Could not find a project with the name \"%s\".", projectName);
                console.println("");
            }
        }
    }
}
