package com.exemplo.gestao.controller.form;

import com.exemplo.gestao.model.Task;

import lombok.Setter;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Setter
public class TaskUpdateForm {
	@NotNull
	@NotBlank
	private String description;

	public Task update(Task task) {

		task.setDescription(this.description);
		task.setChangeDate(LocalDateTime.now());
		
		return task;
	}
}
