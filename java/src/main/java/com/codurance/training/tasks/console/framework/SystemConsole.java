package com.codurance.training.tasks.console.framework;

import com.codurance.training.tasks.console.adapter.Console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class SystemConsole implements Console {
    private final BufferedReader in;
    private final PrintWriter out;

    public SystemConsole(BufferedReader in, PrintWriter out) {
        this.in = in;
        this.out = out;
    }

    @Override
    public String readLine() {
        try {
            return in.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void print(String message) {
        out.print(message);
        out.flush();
    }

    @Override
    public void println(String message) {
        out.println(message);
        out.flush();
    }

    @Override
    public void printf(String format, Object... args) {
        out.printf(format, args);
        out.flush();
    }
}
