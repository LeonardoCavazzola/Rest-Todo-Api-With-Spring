package com.exemplo.gestao.controller.dto;

import com.exemplo.gestao.controller.link.TaskLink;
import com.exemplo.gestao.model.Task;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;

@Getter
public class TaskDetailsDto extends RepresentationModel<TaskDetailsDto> {

	private String description;
	private Boolean concluded;
	private LocalDateTime conclusionDate;
	private LocalDateTime creationDate;
	private LocalDateTime changeDate;

	public TaskDetailsDto(Task task) {
		this.description = task.getDescription();
		this.concluded = task.getConcluded();
		this.conclusionDate = task.getConclusionDate();
		this.creationDate = task.getCreationDate();
		this.changeDate = task.getChangeDate();

		super.add(
				TaskLink.self(task.getId()),
				this.concluded ? TaskLink.deconclude(task.getId()) : TaskLink.conclude(task.getId()),
				TaskLink.update(task.getId()),
				TaskLink.delete(task.getId()),
				TaskLink.readAll(null, null)
		);
	}
}
