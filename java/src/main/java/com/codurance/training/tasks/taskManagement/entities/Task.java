package com.codurance.training.tasks.taskManagement.entities;

public final class Task {
    private final TaskId id;
    private final TaskDescription description;
    private boolean done;

    public Task(TaskId id, TaskDescription description) {
        this.id = id;
        this.description = description;
        this.done = false;
    }

    public TaskId getId() {
        return id;
    }

    public TaskDescription getDescription() {
        return description;
    }

    public boolean isDone() {
        return done;
    }

    public void markAsDone() {
        this.done = true;
    }

    public void markAsUndone() {
        this.done = false;
    }
}
