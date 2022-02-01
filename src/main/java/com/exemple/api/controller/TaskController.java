package com.exemple.api.controller;

import java.net.URI;

import javax.transaction.Transactional;
import javax.validation.Valid;

import com.exemple.api.controller.dto.TaskDto;
import com.exemple.api.model.Task;
import com.exemple.api.controller.dto.TaskDetailsDto;
import com.exemple.api.controller.form.TaskUpdateForm;
import com.exemple.api.controller.form.TaskForm;
import com.exemple.api.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
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

	/**
	 * Endpoint para obter as informações completas de uma Task especifica.
	 *
	 * @param id
	 * @return ResponseEntity
	 */
	@GetMapping("/{id}")
	public ResponseEntity<TaskDetailsDto> readOne(@PathVariable Long id) {
		return this.taskRepository.findById(id)
				.map(task -> ResponseEntity.ok(new TaskDetailsDto(task))) //Se essa id existe, retorna uma ResponseEntity<TaskDetailsDto> com codigo 200(ok).
				.orElseGet(() -> ResponseEntity.notFound().build()); //Se não existe, retorna uma ResponseEntity com codigo 404(not found).
	}

	/**
	 * Endpoint para obter as informações resumidas de todas as Task que correspondem aos filtros.
	 *
	 * @param description
	 * @param concluded
	 * @param paginator
	 * @return Page
	 */
	@GetMapping
	public PagedModel<EntityModel<TaskDto>> readAll(@RequestParam(required = false) String description,
													@RequestParam(required = false) Boolean concluded,
													@PageableDefault(sort = "creationDate", direction = Direction.ASC, page = 0, size = 20) Pageable paginator,
													UriComponentsBuilder uriBuilder) {

		Page<Task> tasks;
		if(description == null){
			if(concluded == null){ //Caso description e concluded são null.
				tasks = this.taskRepository.findAll(paginator);
			}
			else{ //Caso description é null e concluded não.
				tasks = this.taskRepository.findByConcluded(concluded, paginator);
			}
		}
		else{
			if(concluded == null){ //Caso description não é null e concluded é null.
				tasks = this.taskRepository.findByDescriptionContaining(description, paginator);
			}
			else{ //Caso description e concluded não são null.
				tasks = this.taskRepository.findByDescriptionContainingAndConcluded(description, concluded, paginator);
			}
		}
		return TaskDto.buildTaskDtoPage(tasks, uriBuilder); //Converte para, e retorna uma pagina com "HATEOAS" de TaskDto.
	}

	/**
	 * Endpoint para cadastrar novas Tasks.
	 *
	 * @param taskForm
	 * @param uriBuilder
	 * @return ResponseEntity
	 */
	@PostMapping
	@Transactional
	public ResponseEntity<TaskDetailsDto> create(@RequestBody @Valid TaskForm taskForm, UriComponentsBuilder uriBuilder) {
		Task task = taskForm.createNewTask(this.taskRepository); //Cria e salva uma task com as informações enviadas no form.

		URI uri = uriBuilder.path("/tasks/{id}").buildAndExpand(task.getId()).toUri(); //Cria a URI para o objeto Task criado.
		return ResponseEntity.created(uri).body(new TaskDetailsDto(task)); //Retorna uma ResponseEntity<TaskDetailsDto> com codigo 201(Created) e location de acordo com o URI criado.
	}

	/**
	 * Endpoint para alterar uma Tasks especifica.
	 *
	 * @param id
	 * @param taskUpdateForm
	 * @return ResponseEntity
	 */
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<TaskDetailsDto> update(@PathVariable Long id, @RequestBody @Valid TaskUpdateForm taskUpdateForm) {

		return this.taskRepository.findById(id)
				.map(task -> { //Se essa id existe
					TaskDetailsDto dto = new TaskDetailsDto(taskUpdateForm.update(task)); //Atualiza as informações
					return ResponseEntity.ok(dto); //Retorna uma ResponseEntity<TaskDetailsDto> com codigo 200(ok).
				}).orElseGet(() -> ResponseEntity.notFound().build()); //Se não existe, retorna uma ResponseEntity com codigo 404(not found).
	}

	/**
	 * Endpoint para deletar uma Tasks especifica.
	 *
	 * @param id
	 * @return ResponseEntity
	 */
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable Long id) {

		return this.taskRepository.findById(id)
				.map(task -> { //Se essa id existe
					this.taskRepository.delete(task); //Deleta a task.
					return ResponseEntity.ok().build(); //Retorna uma ResponseEntity vazia com codigo 200(ok).
				}).orElseGet(() -> ResponseEntity.notFound().build()); //Se não existe, retorna uma ResponseEntity com codigo 404(not found).
	}

	/**
	 * Endpoint para marcar uma Tasks especifica como concluida.
	 *
	 * @param id
	 * @return ResponseEntity
	 */
	@PostMapping("/{id}/conclude")
	@Transactional
	public ResponseEntity<TaskDetailsDto> conclude(@PathVariable Long id) {

		return this.taskRepository.findById(id)
				.map(task -> { //Se essa id existe
					TaskDetailsDto dto = new TaskDetailsDto(task.conclude()); //Marca a task como concluida.
					return ResponseEntity.ok(dto); //Retorna uma ResponseEntity<TaskDetailsDto> com codigo 200(ok).
				}).orElseGet(() -> ResponseEntity.notFound().build()); //Se não existe, retorna uma ResponseEntity com codigo 404(not found).
	}

	/**
	 * Endpoint para desmarcar a conclusao de uma Tasks especifica.
	 *
	 * @param id
	 * @return ResponseEntity
	 */
	@DeleteMapping("/{id}/conclude")
	@Transactional
	public ResponseEntity<TaskDetailsDto> deconclude(@PathVariable Long id) {

		return this.taskRepository.findById(id)
				.map(task -> { //Se essa id existe
					TaskDetailsDto dto = new TaskDetailsDto(task.deconclude()); //Desmarca a task como concluida.
					return ResponseEntity.ok(dto); //Retorna uma ResponseEntity<TaskDetailsDto> com codigo 200(ok).
				}).orElseGet(() -> ResponseEntity.notFound().build()); //Se não existe, retorna uma ResponseEntity com codigo 404(not found).
	}
}
