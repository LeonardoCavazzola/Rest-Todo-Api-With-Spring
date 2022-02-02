package com.exemple.api.controller.form;

import com.exemple.api.entity.Task;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
public class TaskInput {

    @NotNull
    @NotBlank
    private String description;

    public static Task taskInputToTask(TaskInput taskInput) {
        return new Task(taskInput.description);
    }
}
