package com.codurance.training.tasks.taskManagement.useCase;

import com.codurance.training.tasks.taskManagement.entities.Task;
import com.codurance.training.tasks.taskManagement.entities.TaskListRepository;

public class CheckTaskUseCase {
    private final TaskListRepository repository;

    public CheckTaskUseCase(TaskListRepository repository) {
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
}
