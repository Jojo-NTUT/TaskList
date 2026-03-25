package com.codurance.training.tasks.console.adapter.commands;

import com.codurance.training.tasks.console.adapter.Console;
import com.codurance.training.tasks.taskManagement.entities.Project;
import com.codurance.training.tasks.taskManagement.entities.Task;
import com.codurance.training.tasks.taskManagement.useCase.ShowProjectsUseCase;
import com.codurance.training.tasks.taskManagement.useCase.dto.ProjectDTO;
import com.codurance.training.tasks.taskManagement.useCase.dto.TaskDTO;

public class ShowCommand implements  Command {
    private final ShowProjectsUseCase showProjectsUseCase;
    private final Console console;

    public ShowCommand(ShowProjectsUseCase showProjectsUseCase, Console console) {
        this.showProjectsUseCase = showProjectsUseCase;
        this.console = console;
    }

    @Override
    public void execute() {
        for (ProjectDTO project : showProjectsUseCase.findAllProjects()) {
            console.println(project.name);
            for (TaskDTO task : project.tasks) {
                console.printf("    [%c] %d: %s%n", (task.done ? 'x' : ' '), task.id, task.description);
            }
            console.println("");
        }
    }
}
