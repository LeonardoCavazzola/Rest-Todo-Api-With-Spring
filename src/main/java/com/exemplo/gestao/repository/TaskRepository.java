package com.exemplo.gestao.repository;

import com.exemplo.gestao.modelo.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

    Page<Task> findByDescriptionContaining(String description, Pageable pageable);
}
