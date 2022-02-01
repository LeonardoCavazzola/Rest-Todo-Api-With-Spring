package com.exemple.api.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Class that represents a task.
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private Boolean concluded = false;
    private LocalDateTime conclusionDate;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Task(String description) {
        this.description = description;
    }

    /**
     * Marks this task as completed and returns that same task.
     * If this task is already completed, it just returns.
     *
     * @return Task
     */
    public Task conclude() {
        this.conclusionDate = LocalDateTime.now();
        this.concluded = true;
        return this;
    }

    /**
     * Unmark this task as completed and returns the same task.
     * If this task is not completed, it just returns.
     *
     * @return Task
     */
    public Task deconclude() {
        this.conclusionDate = null;
        this.concluded = false;
        return this;
    }
}
