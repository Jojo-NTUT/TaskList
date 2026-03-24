package com.codurance.training.tasks.taskManagement.entities;

import java.util.ArrayList;
import java.util.List;

public final class Project {
    private final String name;
    private final List<Task> Tasks = new ArrayList<>();

    public Project(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Task> getTasks() {
        return Tasks;
    }

    public void addTask(Task task) {
        Tasks.add(task);
    }

    public Task findTaskById(long id) {
        for (Task task : Tasks) {
            if (task.getId() == id) {
                return task;
            }
        }
        return null;
    }
}
