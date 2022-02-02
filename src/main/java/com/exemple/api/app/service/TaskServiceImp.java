package com.exemple.api.app.service;

import com.exemple.api.domain.entity.Task;
import com.exemple.api.domain.service.TaskService;
import com.exemple.api.app.exception.EntityNotFoundException;
import com.exemple.api.app.repository.TaskRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImp implements TaskService {

    private final TaskRepository taskRepository;

    public TaskServiceImp(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    private Task findEntity(Long id) {
        return taskRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Task findById(Long id) {
        return this.findEntity(id);
    }

    @Override
    public Page<Task> findAllByDescription(String description, Pageable pageable) {
        if (description != null) {
            return taskRepository.findByDescriptionContaining(description, pageable);
        } else {
            return taskRepository.findAll(pageable);
        }
    }

    @Override
    public Task create(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task update(Long id, Task right) {
        return findEntity(id).update(right);
    }

    @Override
    public void delete(Long id) {
        Task entity = this.findEntity(id);
        this.taskRepository.delete(entity);
    }

    @Override
    public Task conclude(Long id) {
        return findEntity(id).conclude();
    }

    @Override
    public Task deconclude(Long id) {
        return findEntity(id).deconclude();
    }
}
