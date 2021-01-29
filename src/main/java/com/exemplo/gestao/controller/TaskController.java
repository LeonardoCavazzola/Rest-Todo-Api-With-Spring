package com.exemplo.gestao.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import com.exemplo.gestao.controller.dto.TaskDetalhesDto;
import com.exemplo.gestao.controller.dto.TaskDto;
import com.exemplo.gestao.controller.form.TaskAtualizacaoForm;
import com.exemplo.gestao.controller.form.TaskForm;
import com.exemplo.gestao.modelo.Task;
import com.exemplo.gestao.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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
	public ResponseEntity<TaskDetalhesDto> readOne(@PathVariable Long id) {
		return this.taskRepository.findById(id)
				.map(task -> ResponseEntity.ok(new TaskDetalhesDto(task)))
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@GetMapping
	public Page<TaskDto> readAll(@RequestParam(required = false) String descricao,
								 @PageableDefault(sort = "creationDate", direction = Direction.ASC, page = 0, size = 20) Pageable paginator) {
		
		if (descricao == null) {
			Page<Task> tasks = this.taskRepository.findAll(paginator);
			return TaskDto.createTaskDtoPage(tasks);
		} else {
			Page<Task> tasks = this.taskRepository.findByDescriptionContaining(descricao, paginator);
			return TaskDto.createTaskDtoPage(tasks);
		}
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<TaskDetalhesDto> create(@RequestBody @Valid TaskForm taskForm, UriComponentsBuilder uriBuilder) {
		Task task = taskForm.createNewTask(this.taskRepository);
		
		URI uri = uriBuilder.path("/tasks/{id}").buildAndExpand(task.getId()).toUri();
		return ResponseEntity.created(uri).body(new TaskDetalhesDto(task));
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<TaskDetalhesDto> update(@PathVariable Long id, @RequestBody @Valid TaskAtualizacaoForm taskAtualizacaoForm) {

		return this.taskRepository.findById(id)
				.map(task -> {
					TaskDetalhesDto dto = new TaskDetalhesDto(taskAtualizacaoForm.update(task));
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
				}).orElseGet(()->ResponseEntity.notFound().build());
	}
}