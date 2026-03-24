package com.codurance.training.tasks.console.adapter;

import com.codurance.training.tasks.taskManagement.entities.Project;
import com.codurance.training.tasks.taskManagement.entities.Task;
import com.codurance.training.tasks.taskManagement.useCase.*;

import java.io.PrintWriter;

public class CommandParsing {
    private final PrintWriter out;
    private final AddProjectUseCase addProjectUseCase;
    private final AddTaskUseCase addTaskUseCase;
    private final CheckTaskUseCase checkTaskUseCase;
    private final UncheckTaskUseCase uncheckTaskUseCase;
    private final ShowProjectsUseCase showProjectsUseCase;
    public CommandParsing(
            PrintWriter out,
            AddProjectUseCase addProjectUseCase,
            AddTaskUseCase addTaskUseCase,
            CheckTaskUseCase checkTaskUseCase,
            UncheckTaskUseCase uncheckTaskUseCase,
            ShowProjectsUseCase showProjectsUseCase) {
        this.out = out;
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
            out.printf("Could not find a task with an ID of %d.%n", id);
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
                out.printf("Could not find a project with the name \"%s\".", projectName);
                out.println();
            }
        }
    }

    private void show() {
        for (Project project : showProjectsUseCase.findAllProjects()) {
            out.println(project.getName());
            for (Task task : project.getTasks()) {
                out.printf("    [%c] %d: %s%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription());
            }
            out.println();
        }
    }

    private void help() {
        out.println("Commands:");
        out.println("  show");
        out.println("  add project <project name>");
        out.println("  add task <project name> <task description>");
        out.println("  check <task ID>");
        out.println("  uncheck <task ID>");
        out.println();
    }

    private void error(String command) {
        out.printf("I don't know what the command \"%s\" is.", command);
        out.println();
    }
}
