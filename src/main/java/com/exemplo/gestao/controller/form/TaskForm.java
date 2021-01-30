package com.exemplo.gestao.controller.form;

import com.exemplo.gestao.model.Task;
import com.exemplo.gestao.repository.TaskRepository;
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
