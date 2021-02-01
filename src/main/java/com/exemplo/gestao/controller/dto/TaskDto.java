package com.exemplo.gestao.controller.dto;

import com.exemplo.gestao.controller.TaskController;
import com.exemplo.gestao.model.Task;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

@Getter
public class TaskDto extends RepresentationModel<TaskDto> {

	private String description;
	private Boolean concluded;

	public TaskDto(Task task) {
		this.description = task.getDescription();
		this.concluded = task.getConcluded();
		super.add(WebMvcLinkBuilder.linkTo(TaskController.class).slash(task.getId()).withSelfRel());
	}

	public static Page<TaskDto> createTaskDtoPage(Page<Task> tasks) {
		return tasks.map(TaskDto::new);
	}
}
