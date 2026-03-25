package com.codurance.training.tasks.taskManagement.useCase;

import com.codurance.training.tasks.taskManagement.entities.*;

public class AddTaskUseCase {
        private final TaskListRepository repository;

        public AddTaskUseCase(TaskListRepository repository) {
            this.repository = repository;
        }

        public boolean addTask(String projectName, String description) {
            ProjectName name = new ProjectName(projectName);
            Project project = repository.findProjectByName(name);
            if (project == null) {
                return false;
            }

            TaskId taskId = new TaskId(repository.nextId());
            TaskDescription taskDescription = new TaskDescription(description);
            project.addTask(new Task(taskId, taskDescription));
            return true;
        }
}
