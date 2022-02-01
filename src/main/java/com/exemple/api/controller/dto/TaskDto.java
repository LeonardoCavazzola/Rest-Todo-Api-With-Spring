package com.exemple.api.controller.dto;

import com.exemple.api.model.Task;
import com.exemple.api.controller.link.TaskLink;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.data.web.HateoasPageableHandlerMethodArgumentResolver;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.web.util.UriComponentsBuilder;

@Getter
public class TaskDto extends RepresentationModel<TaskDto> {

	private String description;
	private Boolean concluded;

	public TaskDto(Task task) {
		this.description = task.getDescription();
		this.concluded = task.getConcluded();

		this.add(TaskLink.self(task.getId()))
				.add(this.concluded ? TaskLink.deconclude(task.getId()) : TaskLink.conclude(task.getId())) // ta concluido? se sim manda o link para "desconcluir", se não mada o link para concluir.
				.add(TaskLink.update(task.getId()))
				.add(TaskLink.delete(task.getId()));
	}

	@Deprecated
	public static Page<TaskDto> createTaskDtoPage(Page<Task> tasks) {
		return tasks.map(TaskDto::new);
	}

	public static PagedModel<EntityModel<TaskDto>> buildTaskDtoPage(Page<Task> tasks,  UriComponentsBuilder uriBuilder) {
		PagedResourcesAssembler<TaskDto> taskDtoPRA = new PagedResourcesAssembler<>(
				new HateoasPageableHandlerMethodArgumentResolver(),
				uriBuilder
						.path("/tasks")
						.build()
		);

		return taskDtoPRA.toModel(tasks.map(TaskDto::new));
	}
}
