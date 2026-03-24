package com.codurance.training.tasks.console.framework;

import com.codurance.training.tasks.console.adapter.Console;
import com.codurance.training.tasks.taskManagement.framework.InMemoryRepository;
import com.codurance.training.tasks.taskManagement.useCase.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class TaskList implements Runnable {
    private static final String QUIT = "quit";
    private static final String PROMPT = "> ";

    private final Console console;
    private final CommandFactory commandFactory;



    public TaskList(Console console, CommandFactory commandFactory) {
        this.console = console;
        this.commandFactory = commandFactory;
    }

    public static void main(String[] args) {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        Console console=new SystemConsole(in, out);

        InMemoryRepository repository = new InMemoryRepository();
        //use case
        AddProjectUseCase addProjectUseCase = new AddProjectUseCase(repository);
        AddTaskUseCase addTaskUseCase = new AddTaskUseCase(repository);
        CheckTaskUseCase checkTaskUseCase = new CheckTaskUseCase(repository);
        UncheckTaskUseCase uncheckTaskUseCase = new UncheckTaskUseCase(repository);
        ShowProjectsUseCase showProjectsUseCase = new ShowProjectsUseCase(repository);

        CommandFactory commandParsing = new CommandFactory(console,
                addProjectUseCase,
                addTaskUseCase,
                checkTaskUseCase,
                uncheckTaskUseCase,
                showProjectsUseCase);

        new TaskList(console,commandParsing).run();
    }

    @Override
    public void run() {
        while (true) {
            console.print(PROMPT);

            String command = console.readLine(); // 直接呼叫，不需要 try-catch IOException

            if (command == null || QUIT.equals(command)) {
                break;
            }
            commandFactory.create(command).execute();
        }
    }

}
