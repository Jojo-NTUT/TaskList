package com.codurance.training.tasks.console.framework;

import com.codurance.training.tasks.console.adapter.commandParsing;
import com.codurance.training.tasks.taskManagement.framework.InMemoryRepository;
import com.codurance.training.tasks.taskManagement.useCase.taskList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class taskListConsole implements Runnable {
    private static final String QUIT = "quit";
    private static final String PROMPT = "> ";

    private final BufferedReader in;
    private final PrintWriter out;
    private final commandParsing CommandParsing;



    public taskListConsole(BufferedReader in, PrintWriter out, taskList taskList) {
        this.in = in;
        this.out = out;
        this.CommandParsing = new commandParsing(out, taskList);
    }

    public static void main(String[] args) {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        new taskListConsole(in, out, new taskList(new InMemoryRepository())).run();
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
            CommandParsing.execute(command);
        }
    }

}
