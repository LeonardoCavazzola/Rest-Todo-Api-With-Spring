package com.exemple.api.controller.form;

import com.exemple.api.model.Task;
import com.exemple.api.repository.TaskRepository;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
public class TaskForm {

    @NotNull
    @NotBlank
    private String description;

    public Task createNewTask(TaskRepository taskRepository) {
        return taskRepository.save(new Task(this.description));
    }
}
