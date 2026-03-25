package com.codurance.training.tasks.taskManagement.entities;

import java.util.ArrayList;
import java.util.List;

public final class Project {
    private final ProjectName name;
    private final List<Task> Tasks = new ArrayList<>();

    public Project(ProjectName name) {
        this.name = name;
    }

    public ProjectName getName() {
        return name;
    }

    public List<Task> getTasks() {
        return Tasks;
    }

    public void addTask(Task task) {
        Tasks.add(task);
    }

}
