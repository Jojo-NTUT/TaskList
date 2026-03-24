package com.codurance.training.tasks.taskManagement.useCase;

import com.codurance.training.tasks.taskManagement.entities.project;
import com.codurance.training.tasks.taskManagement.entities.task;
import com.codurance.training.tasks.taskManagement.entities.taskListRepository;
import com.codurance.training.tasks.taskManagement.framework.InMemoryRepository;

import java.util.*;

public final class taskList {

    private final taskListRepository repository;

    public List<project> findAllProjects() {
        return repository.findAllProjects();
    }


    public taskList(taskListRepository repository) {
        this.repository = repository;
    }

    public void addProject(String name) {
        repository.findAllProjects().add(new project(name));
    }

    public boolean addTask(String projectName, String description) {
        project project = repository.findProjectByName(projectName);
        if (project == null) {
            return false;
    }

        project.addTask(new task(repository.nextId(), description));
        return true;
    }

    public boolean checkTask(long id) {
        task task = repository.findTaskById(id);
        if (task == null) {
            return false;
    }
        task.markAsDone();
        return true;
    }

    public boolean uncheckTask(long id) {
        task task = repository.findTaskById(id);
        if (task == null) {
            return false;
        }
        task.markAsUndone();
        return true;
    }
}
