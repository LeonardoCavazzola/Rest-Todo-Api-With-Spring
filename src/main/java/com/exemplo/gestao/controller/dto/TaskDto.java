package com.exemplo.gestao.controller.dto;

import com.exemplo.gestao.controller.link.TaskLink;
import com.exemplo.gestao.model.Task;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.RepresentationModel;

@Getter
public class TaskDto extends RepresentationModel<TaskDto> {

	private String description;
	private Boolean concluded;

	public TaskDto(Task task) {
		this.description = task.getDescription();
		this.concluded = task.getConcluded();
		super.add(
				TaskLink.self(task.getId()),
				TaskLink.conclude(task.getId()),
				TaskLink.update(task.getId()),
				TaskLink.delete(task.getId())
		);
	}

	public static Page<TaskDto> createTaskDtoPage(Page<Task> tasks) {
		return tasks.map(TaskDto::new);
	}
}
