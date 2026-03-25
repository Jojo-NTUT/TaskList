package com.codurance.training.tasks.taskManagement.entities;

public class TaskDescription {
    private final String description;

    public TaskDescription(String description) {
        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("Description cannot be empty");
        }
        this.description = description;
    }

    public String value() {
        return description;
    }
}
