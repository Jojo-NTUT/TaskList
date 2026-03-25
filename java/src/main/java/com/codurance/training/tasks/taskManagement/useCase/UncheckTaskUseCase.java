package com.codurance.training.tasks.taskManagement.useCase;

import com.codurance.training.tasks.taskManagement.entities.Task;
import com.codurance.training.tasks.taskManagement.entities.TaskId;
import com.codurance.training.tasks.taskManagement.entities.TaskListRepository;

public class UncheckTaskUseCase {
    private final TaskListRepository repository;

    public UncheckTaskUseCase(TaskListRepository repository) {
        this.repository = repository;
    }

    public boolean uncheckTask(long id) {
        TaskId taskId = new TaskId(id);
        Task task = repository.findTaskById(taskId);
        if (task == null) {
            return false;
        }
        task.markAsUndone();
        return true;
    }
}
