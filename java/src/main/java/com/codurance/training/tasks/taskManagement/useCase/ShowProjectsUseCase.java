package com.codurance.training.tasks.taskManagement.useCase;

import com.codurance.training.tasks.taskManagement.entities.Project;
import com.codurance.training.tasks.taskManagement.entities.TaskListRepository;

import java.util.List;

public class ShowProjectsUseCase {
    private final TaskListRepository repository;

    public ShowProjectsUseCase(TaskListRepository repository) {
        this.repository = repository;
    }

    public List<Project> findAllProjects() {
        return repository.findAllProjects();
    }
}
