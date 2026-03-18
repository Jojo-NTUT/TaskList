package com.codurance.training.tasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class TaskListConsole implements Runnable {
    private static final String QUIT = "quit";
    private static final String PROMPT = "> ";

    private final BufferedReader in;
    private final PrintWriter out;
    private final TaskList taskList;

    public TaskListConsole(BufferedReader in, PrintWriter out, TaskList taskList) {
        this.in = in;
        this.out = out;
        this.taskList = taskList;
    }

    public static void main(String[] args) {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        new TaskListConsole(in, out, new TaskList()).run();
    }

    @Override
    public void run() {
        while (true) {
            out.print(PROMPT);
            out.flush();

            String command;
            try {
                command = in.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (command == null || QUIT.equals(command)) {
                break;
            }
            execute(command);
        }
    }

    private void execute(String commandLine) {
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
        boolean updated = done ? taskList.checkTask(id) : taskList.uncheckTask(id);

        if (!updated) {
            out.printf("Could not find a task with an ID of %d.%n", id);
        }
    }

    private void add(String commandLine) {
        String[] subcommandRest = commandLine.split(" ", 2);
        String subcommand = subcommandRest[0];

        if ("project".equals(subcommand)) {
            taskList.addProject(subcommandRest[1]);
        } else if ("task".equals(subcommand)) {
            String[] projectTask = subcommandRest[1].split(" ", 2);
            String projectName = projectTask[0];
            String description = projectTask[1];

            if (!taskList.addTask(projectName, description)) {
                out.printf("Could not find a project with the name \"%s\".", projectName);
                out.println();
            }
        }
    }

    private void show() {
        for (Project project : taskList.getProjects()) {
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
