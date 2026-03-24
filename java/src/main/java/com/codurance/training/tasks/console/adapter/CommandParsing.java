package com.codurance.training.tasks.console.adapter;

import com.codurance.training.tasks.taskManagement.entities.Project;
import com.codurance.training.tasks.taskManagement.entities.Task;
import com.codurance.training.tasks.taskManagement.useCase.*;

import java.io.PrintWriter;

public class CommandParsing {
    private final Console console;
    private final AddProjectUseCase addProjectUseCase;
    private final AddTaskUseCase addTaskUseCase;
    private final CheckTaskUseCase checkTaskUseCase;
    private final UncheckTaskUseCase uncheckTaskUseCase;
    private final ShowProjectsUseCase showProjectsUseCase;
    public CommandParsing(
            Console console,
            AddProjectUseCase addProjectUseCase,
            AddTaskUseCase addTaskUseCase,
            CheckTaskUseCase checkTaskUseCase,
            UncheckTaskUseCase uncheckTaskUseCase,
            ShowProjectsUseCase showProjectsUseCase) {
        this.console = console;
        this.addProjectUseCase = addProjectUseCase;
        this.addTaskUseCase = addTaskUseCase;
        this.checkTaskUseCase = checkTaskUseCase;
        this.uncheckTaskUseCase = uncheckTaskUseCase;
        this.showProjectsUseCase = showProjectsUseCase;
    }

    public void execute(String commandLine) {
        String[] commandRest = commandLine.split(" ", 2);
        String command = commandRest[0];

        switch (command) {
            case "show":
                show();
                break;
            case "add":
                add(commandRest[1]);
                break;
            case "check":
                updateTaskStatus(commandRest[1], true);
                break;
            case "uncheck":
                updateTaskStatus(commandRest[1], false);
                break;
            case "help":
                help();
                break;
            default:
                error(command);
                break;
        }
    }

    private void updateTaskStatus(String idString, boolean done) {
        long id = Long.parseLong(idString);
        boolean updated = done ? checkTaskUseCase.checkTask(id) : uncheckTaskUseCase.uncheckTask(id);

        if (!updated) {
            console.printf("Could not find a task with an ID of %d.%n", id);
        }
    }

    private void add(String commandLine) {
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

    private void show() {
        for (Project project : showProjectsUseCase.findAllProjects()) {
            console.println(project.getName());
            for (Task task : project.getTasks()) {
                console.printf("    [%c] %d: %s%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription());
            }
            console.println("");
        }
    }

    private void help() {
        console.println("Commands:");
        console.println("  show");
        console.println("  add project <project name>");
        console.println("  add task <project name> <task description>");
        console.println("  check <task ID>");
        console.println("  uncheck <task ID>");
        console.println("");
    }

    private void error(String command) {
        console.printf("I don't know what the command \"%s\" is.", command);
        console.println("");
    }
}
