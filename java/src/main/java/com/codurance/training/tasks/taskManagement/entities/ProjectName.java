package com.codurance.training.tasks.taskManagement.entities;

public class ProjectName {
    private final String name;

    public ProjectName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Project name cannot be empty");
        }
        this.name = name;
    }

    public String value() {
        return name;
    }
}
