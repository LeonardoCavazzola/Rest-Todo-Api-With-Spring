package com.exemplo.gestao.controller.dto;

import com.exemplo.gestao.model.Task;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
public class TaskDto {

	private String description;
	private Boolean concluded;

	public TaskDto(Task task) {
		this.description = task.getDescription();
		this.concluded = task.getConcluded();
	}

	public static Page<TaskDto> createTaskDtoPage(Page<Task> tasks) {
		return tasks.map(TaskDto::new);
	}
}
