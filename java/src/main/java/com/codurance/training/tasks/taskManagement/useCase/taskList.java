package com.codurance.training.tasks.taskManagement.useCase;

import com.codurance.training.tasks.taskManagement.entities.project;
import com.codurance.training.tasks.taskManagement.entities.task;

import java.util.*;

public final class taskList {
    private final List<project> projects = new ArrayList<>();
    private long lastId = 0;

    public List<project> getProjects() {
        return projects;
    }

    public void addProject(String name) {
        projects.add(new project(name));
    }

    public boolean addTask(String projectName, String description) {
        project project = findProjectByName(projectName);
        if (project == null) {
            return false;
    }

        project.addTask(new task(nextId(), description));
        return true;
    }

    public boolean checkTask(long id) {
        task task = findTaskById(id);
        if (task == null) {
            return false;
    }
        task.markAsDone();
        return true;
    }

    public boolean uncheckTask(long id) {
        task task = findTaskById(id);
        if (task == null) {
            return false;
        }
        task.markAsUndone();
        return true;
    }

    private project findProjectByName(String name) {
        for (project project : projects) {
            if (project.getName().equals(name)) {
                return project;
            }
        }
        return null;
    }

    private task findTaskById(long id) {
        for (project project : projects) {
            task task = project.findTaskById(id);
            if (task != null) {
                return task;
            }
        }
        return null;
    }

    private long nextId() {
        return ++lastId;
    }
}
