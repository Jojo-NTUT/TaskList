package com.codurance.training.tasks.taskManagement.entities;

public class TaskId {
    private final long id;

    public TaskId(long id) {
        if (id <= 0) throw new IllegalArgumentException();
        this.id = id;
    }

    public long value() {
        return id;
    }
}
