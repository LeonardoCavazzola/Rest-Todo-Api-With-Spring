package com.exemplo.gestao.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

/**
 * Classe que representa uma tarefa.
 */

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private Boolean concluded = false;
    private LocalDateTime conclusionDate;
    private LocalDateTime creationDate = LocalDateTime.now();
    private LocalDateTime changeDate = LocalDateTime.now();

    public Task(String description) {
        this.description = description;
    }

    /**
     * Marca essa task como concluida e retorna essa mesma task.
     * Caso essa task já esteja concluida, apenas retorna.
     *
     * @return Task
     */
    public Task conclude(){
        if (!concluded) {
            this.changeDate = LocalDateTime.now();
            this.conclusionDate = LocalDateTime.now();
            this.concluded = true;
        }
        return this;
    }

    /**
     * Desmarca essa task como concluida e retorna essa mesma task.
     * Caso essa task não esteja concluida, apenas retorna.
     *
     * @return Task
     */
    public Task deconclude() {
        if (concluded) {
            this.changeDate = LocalDateTime.now();
            this.conclusionDate = null;
            this.concluded = false;
        }
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        return id.equals(task.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
