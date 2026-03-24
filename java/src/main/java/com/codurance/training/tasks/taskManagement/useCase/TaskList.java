package com.codurance.training.tasks.taskManagement.useCase;

import com.codurance.training.tasks.taskManagement.entities.Project;
import com.codurance.training.tasks.taskManagement.entities.Task;
import com.codurance.training.tasks.taskManagement.entities.TaskListRepository;

import java.util.*;

public final class TaskList {

    private final TaskListRepository repository;

    public List<Project> findAllProjects() {
        return repository.findAllProjects();
    }

    public TaskList(TaskListRepository repository) {
        this.repository = repository;
    }


    public boolean checkTask(long id) {
        Task task = repository.findTaskById(id);
        if (task == null) {
            return false;
    }
        task.markAsDone();
        return true;
    }

    public boolean uncheckTask(long id) {
        Task task = repository.findTaskById(id);
        if (task == null) {
            return false;
        }
        task.markAsUndone();
        return true;
    }
}
