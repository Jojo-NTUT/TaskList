package com.codurance.training.tasks.taskManagement.useCase;

import com.codurance.training.tasks.taskManagement.entities.Project;
import com.codurance.training.tasks.taskManagement.entities.ProjectName;
import com.codurance.training.tasks.taskManagement.entities.TaskListRepository;

public class AddProjectUseCase {
    private final TaskListRepository repository;

    public AddProjectUseCase(TaskListRepository repository) {
        this.repository = repository;
    }

    public void addProject(String name) {
        ProjectName projectName = new ProjectName(name);
        repository.findAllProjects().add(new Project(projectName));
    }
}
