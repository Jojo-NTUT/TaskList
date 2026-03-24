package com.codurance.training.tasks.taskManagement.framework;

import com.codurance.training.tasks.taskManagement.entities.Project;
import com.codurance.training.tasks.taskManagement.entities.Task;
import com.codurance.training.tasks.taskManagement.entities.TaskListRepository;
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
    public Project findProjectByName(String name) {
        for (Project project : Projects) {
            if (project.getName().equals(name)) {
                return project;
            }
        }
        return null;
    }

    @Override
    public Task findTaskById(long id) {
        for (Project project : Projects) {
            for (Task task : project.getTasks()) {
                if (task.getId() == id) {
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
