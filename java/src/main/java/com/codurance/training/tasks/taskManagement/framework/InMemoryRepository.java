package com.codurance.training.tasks.taskManagement.framework;

import com.codurance.training.tasks.taskManagement.entities.project;
import com.codurance.training.tasks.taskManagement.entities.task;
import com.codurance.training.tasks.taskManagement.entities.taskListRepository;
import java.util.ArrayList;
import java.util.List;

public class InMemoryRepository implements taskListRepository {
    private final List<project> projects = new ArrayList<>();
    private long lastId = 0;

    @Override
    public List<project> findAllProjects() {
        return projects;
    }

    @Override
    public project findProjectByName(String name) {
        for (project project : projects) {
            if (project.getName().equals(name)) {
                return project;
            }
        }
        return null;
    }

    @Override
    public task findTaskById(long id) {
        for (project project : projects) {
            for (task task : project.getTasks()) {
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
