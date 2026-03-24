package com.codurance.training.tasks.console.adapter;

public interface Console {
    String readLine();
    void print(String message);
    void println(String message);
    void printf(String format, Object... args);
}
