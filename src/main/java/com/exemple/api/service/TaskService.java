package com.exemple.api.service;

import com.exemple.api.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TaskService {
    Task findById(Long id);
    Page<Task> findAllByDescription(String description, Pageable pageable);
    Task create(Task task);
    Task update(Long id, Task right);
    void delete(Long id);
    Task conclude(Long id);
    Task deconclude(Long id);
}
