package com.codurance.training.tasks.taskManagement.entities;

public final class task {
    private final long id;
    private final String description;
    private boolean done;

    public task(long id, String description) {
        this.id = id;
        this.description = description;
        this.done = false;
    }

    public long getId() {
        return id;
    }

    public String getDescription() {
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
