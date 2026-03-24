package com.codurance.training.tasks.console.framework;

import com.codurance.training.tasks.console.adapter.CommandParsing;
import com.codurance.training.tasks.taskManagement.framework.InMemoryRepository;
import com.codurance.training.tasks.taskManagement.useCase.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class TaskListConsole implements Runnable {
    private static final String QUIT = "quit";
    private static final String PROMPT = "> ";

    private final BufferedReader in;
    private final PrintWriter out;
    private final CommandParsing commandParsing;



    public TaskListConsole(BufferedReader in, PrintWriter out, CommandParsing commandParsing) {
        this.in = in;
        this.out = out;
        this.commandParsing = commandParsing;
    }

    public static void main(String[] args) {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        InMemoryRepository repository = new InMemoryRepository();
        //use case
        AddProjectUseCase addProjectUseCase = new AddProjectUseCase(repository);
        AddTaskUseCase addTaskUseCase = new AddTaskUseCase(repository);
        CheckTaskUseCase checkTaskUseCase = new CheckTaskUseCase(repository);
        UncheckTaskUseCase uncheckTaskUseCase = new UncheckTaskUseCase(repository);
        ShowProjectsUseCase showProjectsUseCase = new ShowProjectsUseCase(repository);

        CommandParsing commandParsing = new CommandParsing(out,
                addProjectUseCase,
                addTaskUseCase,
                checkTaskUseCase,
                uncheckTaskUseCase,
                showProjectsUseCase);

        new TaskListConsole(in, out,commandParsing).run();
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
            commandParsing.execute(command);
        }
    }

}
