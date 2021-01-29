package com.exemplo.gestao.controller.dto;

import com.exemplo.gestao.modelo.Task;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TaskDetalhesDto {

	private String description;
	private Boolean concluded;
	private LocalDateTime conclusionDate;
	private LocalDateTime creationDate;
	private LocalDateTime changeDate;

	public TaskDetalhesDto(Task task) {
		this.description = task.getDescription();
		this.concluded = task.getConcluded();
		this.conclusionDate = task.getConclusionDate();
		this.creationDate = task.getCreationDate();
		this.changeDate = task.getChangeDate();
	}
}
