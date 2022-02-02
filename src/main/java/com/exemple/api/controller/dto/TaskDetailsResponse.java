package com.exemple.api.controller.dto;

import com.exemple.api.controller.link.TaskLink;
import com.exemple.api.entity.Task;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;

@Getter
public class TaskDetailsResponse extends RepresentationModel<TaskDetailsResponse> {

    private final Long id;
    private final String description;
    private final Boolean concluded;
    private final LocalDateTime conclusionDate;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public TaskDetailsResponse(Task task) {
        this.id = task.getId();
        this.description = task.getDescription();
        this.concluded = task.getConcluded();
        this.conclusionDate = task.getConclusionDate();
        this.createdAt = task.getCreatedAt();
        this.updatedAt = task.getUpdatedAt();

        this.add(TaskLink.self(task.getId()));
        this.add(this.concluded ? TaskLink.deconclude(task.getId()) : TaskLink.conclude(task.getId()));
        this.add(TaskLink.update(task.getId()));
        this.add(TaskLink.delete(task.getId()));
    }

    public static TaskDetailsResponse taskToTaskDetailsResponse(Task task) {
        return new TaskDetailsResponse(task);
    }
}
