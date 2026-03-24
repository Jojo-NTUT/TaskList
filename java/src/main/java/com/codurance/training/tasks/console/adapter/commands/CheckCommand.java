package com.codurance.training.tasks.console.adapter.commands;

import com.codurance.training.tasks.console.adapter.Console;
import com.codurance.training.tasks.taskManagement.useCase.CheckTaskUseCase;
import com.codurance.training.tasks.taskManagement.useCase.UncheckTaskUseCase;

public class CheckCommand implements  Command {

    private final Console console;
    private final String idString;
    private final boolean done;
    private final CheckTaskUseCase checkTaskUseCase;

    private final UncheckTaskUseCase uncheckTaskUseCase;

    public CheckCommand(String idString, boolean done, CheckTaskUseCase checkTaskUseCase, UncheckTaskUseCase uncheckTaskUseCase,Console console) {
        this.idString = idString;
        this.done = done;
        this.checkTaskUseCase = checkTaskUseCase;
        this.uncheckTaskUseCase = uncheckTaskUseCase;
        this.console = console;
    }

    @Override
    public void execute() {
        long id = Long.parseLong(idString);
        boolean updated = done ? checkTaskUseCase.checkTask(id) : uncheckTaskUseCase.uncheckTask(id);

        if (!updated) {
            console.printf("Could not find a task with an ID of %d.%n", id);
        }
    }
}
