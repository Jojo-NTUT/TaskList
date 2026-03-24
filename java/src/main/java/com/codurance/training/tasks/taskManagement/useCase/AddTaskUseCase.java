package com.codurance.training.tasks.taskManagement.useCase;

import com.codurance.training.tasks.taskManagement.entities.Project;
import com.codurance.training.tasks.taskManagement.entities.Task;
import com.codurance.training.tasks.taskManagement.entities.TaskListRepository;

public class AddTaskUseCase {
        private final TaskListRepository repository;

        public AddTaskUseCase(TaskListRepository repository) {
            this.repository = repository;
        }

        public boolean addTask(String projectName, String description) {
            Project project = repository.findProjectByName(projectName);
            if (project == null) {
                return false;
            }

            project.addTask(new Task(repository.nextId(), description));
            return true;
        }
}
