package com.codurance.training.tasks.taskManagement.framework;

import com.codurance.training.tasks.taskManagement.entities.*;

import java.util.ArrayList;
import java.util.List;

public class InMemoryRepository implements TaskListRepository {
    private final List<Project> Projects = new ArrayList<>();
    private long lastId = 0;

    @Override
    public List<Project> findAllProjects() {
        return Projects;
    }

    @Override
    public Project findProjectByName(ProjectName name) {
        for (Project project : Projects) {
            if (project.getName().value().equals(name.value())) {
                return project;
            }
        }
        return null;
    }

    @Override
    public Task findTaskById(TaskId id) {
        for (Project project : Projects) {
            for (Task task : project.getTasks()) {
                if (task.getId().value() == id.value()) {
                    return task;
                }
            }
        }
        return null;
    }

    @Override
    public long nextId() {
        return ++lastId;
    }

}
