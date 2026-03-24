package com.codurance.training.tasks.taskManagement.entities;

import java.util.ArrayList;
import java.util.List;

public final class project {
    private final String name;
    private final List<task> tasks = new ArrayList<>();

    public project(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<task> getTasks() {
        return tasks;
    }

    public void addTask(task task) {
        tasks.add(task);
    }

    public task findTaskById(long id) {
        for (task task : tasks) {
            if (task.getId() == id) {
                return task;
            }
        }
        return null;
    }
}
