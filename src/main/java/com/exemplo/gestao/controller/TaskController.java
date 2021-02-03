package com.exemplo.gestao.controller;

import java.net.URI;

import javax.transaction.Transactional;
import javax.validation.Valid;

import com.exemplo.gestao.controller.dto.TaskDetailsDto;
import com.exemplo.gestao.controller.dto.TaskDto;
import com.exemplo.gestao.controller.form.TaskUpdateForm;
import com.exemplo.gestao.controller.form.TaskForm;
import com.exemplo.gestao.model.Task;
import com.exemplo.gestao.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/tasks")
public class TaskController {
	
	@Autowired
	private TaskRepository taskRepository;

	@GetMapping("/{id}")
	public ResponseEntity<TaskDetailsDto> readOne(@PathVariable Long id) {
		return this.taskRepository.findById(id)
				.map(task -> ResponseEntity.ok(new TaskDetailsDto(task)))
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@GetMapping
	public Page<TaskDto> readAll(@RequestParam(defaultValue = "", required = false) String description,
								 @RequestParam(defaultValue = "false", required = false) Boolean concluded,
								 @PageableDefault(sort = "creationDate", direction = Direction.ASC, page = 0, size = 20) Pageable paginator) {

		Page<Task> tasks = this.taskRepository.findByDescriptionContainingAndConcluded(description, concluded, paginator);
		return TaskDto.createTaskDtoPage(tasks);
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<TaskDetailsDto> create(@RequestBody @Valid TaskForm taskForm, UriComponentsBuilder uriBuilder) {
		Task task = taskForm.createNewTask(this.taskRepository);
		
		URI uri = uriBuilder.path("/tasks/{id}").buildAndExpand(task.getId()).toUri();
		return ResponseEntity.created(uri).body(new TaskDetailsDto(task));
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<TaskDetailsDto> update(@PathVariable Long id, @RequestBody @Valid TaskUpdateForm taskUpdateForm) {

		return this.taskRepository.findById(id)
				.map(task -> {
					TaskDetailsDto dto = new TaskDetailsDto(taskUpdateForm.update(task));
					return ResponseEntity.ok(dto);
				}).orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable Long id) {

		return this.taskRepository.findById(id)
				.map(task -> {
					this.taskRepository.delete(task);
					return ResponseEntity.ok().build();
				}).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PutMapping("/{id}/conclude") //A escolha do put foi devido o metodo ser indepotente e unsafe.
	@Transactional
	public ResponseEntity<TaskDetailsDto> conclude(@PathVariable Long id) {

		return this.taskRepository.findById(id)
				.map(task -> {
					TaskDetailsDto dto = new TaskDetailsDto(task.conclude());
					return ResponseEntity.ok(dto);
				}).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PutMapping("/{id}/deconclude") //A escolha do put foi devido o metodo ser indepotente e unsafe.
	@Transactional
	public ResponseEntity<TaskDetailsDto> deconclude(@PathVariable Long id) {

		return this.taskRepository.findById(id)
				.map(task -> {
					TaskDetailsDto dto = new TaskDetailsDto(task.deconclude());
					return ResponseEntity.ok(dto);
				}).orElseGet(() -> ResponseEntity.notFound().build());
	}
}