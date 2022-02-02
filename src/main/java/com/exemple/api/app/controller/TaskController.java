package com.exemple.api.app.controller;

import java.net.URI;

import javax.transaction.Transactional;
import javax.validation.Valid;

import com.exemple.api.app.controller.dto.TaskResponse;
import com.exemple.api.domain.entity.Task;
import com.exemple.api.app.controller.dto.TaskDetailsResponse;
import com.exemple.api.app.controller.form.TaskInput;
import com.exemple.api.domain.service.TaskService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    private URI generateURI(Long id) {
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/tasks/")
                .path(id.toString())
                .build()
                .toUri();
    }

    /**
     * Endpoint para obter as informações completas de uma Task especifica.
     *
     * @param id
     * @return ResponseEntity
     */
    @GetMapping("/{id}")
    public ResponseEntity<TaskDetailsResponse> readOne(@PathVariable Long id) {
        Task task = this.taskService.findById(id);
        TaskDetailsResponse response = TaskDetailsResponse.taskToTaskDetailsResponse(task);
        return ResponseEntity.ok(response);
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
    public PagedModel<EntityModel<TaskResponse>> readAll(@RequestParam(required = false) String description, Pageable pageable) {

        Page<Task> tasks = this.taskService.findAllByDescription(description, pageable);
        return TaskResponse.pageToResponseHateoasPage(tasks);
    }

    /**
     * Endpoint para cadastrar novas Tasks.
     *
     * @param taskInput
     * @param uriBuilder
     * @return ResponseEntity
     */
    @PostMapping
    public ResponseEntity<TaskDetailsResponse> create(@RequestBody @Valid TaskInput taskInput) {
        Task task = TaskInput.taskInputToTask(taskInput);
        task = taskService.create(task);
        TaskDetailsResponse response = new TaskDetailsResponse(task);
        URI uri = generateURI(task.getId());
        return ResponseEntity.created(uri).body(response);
    }

    /**
     * Endpoint para alterar uma Tasks especifica.
     *
     * @param id
     * @param taskUpdateForm
     * @return ResponseEntity
     */
    @PutMapping("/{id}")
    public ResponseEntity<TaskDetailsResponse> update(@PathVariable Long id, @RequestBody @Valid TaskInput taskInput) {
        Task task = TaskInput.taskInputToTask(taskInput);
        task = taskService.update(id, task);
        TaskDetailsResponse response = new TaskDetailsResponse(task);
        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint para deletar uma Tasks especifica.
     *
     * @param id
     * @return ResponseEntity
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        this.taskService.delete(id);
        return ResponseEntity.ok().build();
    }

    /**
     * Endpoint para marcar uma Tasks especifica como concluida.
     *
     * @param id
     * @return ResponseEntity
     */
    @PutMapping("/{id}/conclude")
    @Transactional
    public ResponseEntity<TaskDetailsResponse> conclude(@PathVariable Long id) {
        Task task = this.taskService.conclude(id);
        TaskDetailsResponse response = TaskDetailsResponse.taskToTaskDetailsResponse(task);
        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint para desmarcar a conclusao de uma Tasks especifica.
     *
     * @param id
     * @return ResponseEntity
     */
    @PutMapping("/{id}/deconclude")
    @Transactional
    public ResponseEntity<TaskDetailsResponse> deconclude(@PathVariable Long id) {
        Task task = this.taskService.deconclude(id);
        TaskDetailsResponse response = TaskDetailsResponse.taskToTaskDetailsResponse(task);
        return ResponseEntity.ok(response);
    }
}
