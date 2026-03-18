package com.codurance.training.tasks;

import java.util.*;

public final class TaskList {
    private final List<Project> projects = new ArrayList<>();
    private long lastId = 0;

    public List<Project> getProjects() {
        return projects;
    }

    public void addProject(String name) {
        projects.add(new Project(name));
    }

    public boolean addTask(String projectName, String description) {
        Project project = findProjectByName(projectName);
        if (project == null) {
            return false;
    }

        project.addTask(new Task(nextId(), description));
        return true;
    }

    public boolean checkTask(long id) {
        Task task = findTaskById(id);
        if (task == null) {
            return false;
    }
        task.markAsDone();
        return true;
    }

    public boolean uncheckTask(long id) {
        Task task = findTaskById(id);
        if (task == null) {
            return false;
        }
        task.markAsUndone();
        return true;
    }

    private Project findProjectByName(String name) {
        for (Project project : projects) {
            if (project.getName().equals(name)) {
                return project;
            }
        }
        return null;
    }

    private Task findTaskById(long id) {
        for (Project project : projects) {
            Task task = project.findTaskById(id);
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
