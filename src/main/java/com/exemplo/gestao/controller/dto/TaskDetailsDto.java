package com.exemplo.gestao.controller.dto;

import com.exemplo.gestao.model.Task;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TaskDetailsDto {

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
	}
}
