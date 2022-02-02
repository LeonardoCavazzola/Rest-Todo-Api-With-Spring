package com.exemple.api.app.repository;

import com.exemple.api.domain.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Page<Task> findByDescriptionContaining(String description, Pageable pageable);
}
