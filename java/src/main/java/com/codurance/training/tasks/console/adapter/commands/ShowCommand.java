package com.codurance.training.tasks.console.adapter.commands;

import com.codurance.training.tasks.console.adapter.Console;
import com.codurance.training.tasks.taskManagement.entities.Project;
import com.codurance.training.tasks.taskManagement.entities.Task;
import com.codurance.training.tasks.taskManagement.useCase.ShowProjectsUseCase;

public class ShowCommand implements  Command {
    private final ShowProjectsUseCase showProjectsUseCase;
    private final Console console;

    public ShowCommand(ShowProjectsUseCase showProjectsUseCase, Console console) {
        this.showProjectsUseCase = showProjectsUseCase;
        this.console = console;
    }

    @Override
    public void execute() {
        for (Project project : showProjectsUseCase.findAllProjects()) {
            console.println(project.getName());
            for (Task task : project.getTasks()) {
                console.printf("    [%c] %d: %s%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription());
            }
            console.println("");
        }
    }
}
