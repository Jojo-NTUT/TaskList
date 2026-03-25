package com.codurance.training.tasks.taskManagement.useCase;

import com.codurance.training.tasks.taskManagement.entities.Project;
import com.codurance.training.tasks.taskManagement.entities.Task;
import com.codurance.training.tasks.taskManagement.entities.TaskListRepository;
import com.codurance.training.tasks.taskManagement.useCase.dto.ProjectDTO;
import com.codurance.training.tasks.taskManagement.useCase.dto.TaskDTO;

import java.util.ArrayList;
import java.util.List;

public class ShowProjectsUseCase {
    private final TaskListRepository repository;

    public ShowProjectsUseCase(TaskListRepository repository) {
        this.repository = repository;
    }

    public List<ProjectDTO> findAllProjects() {
        List<Project> projects = repository.findAllProjects();
        List<ProjectDTO> result = new ArrayList<>();

        for (Project project : projects) {
            ProjectDTO p = new ProjectDTO();
            p.name = project.getName().value();
            p.tasks = new ArrayList<>();
            for (Task task : project.getTasks()) {
                TaskDTO t = new TaskDTO();
                t.id = task.getId().value();
                t.description = task.getDescription().value();
                t.done = task.isDone();
                p.tasks.add(t);
            }

            result.add(p);
        }
        return result;
    }
}
