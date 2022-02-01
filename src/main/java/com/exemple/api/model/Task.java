package com.exemple.api.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Classe que representa uma tarefa.
 */

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@Entity
public class Task{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    private String description;

    @NotNull
    private Boolean concluded = false;
    private LocalDateTime conclusionDate;

    @NotNull
    private LocalDateTime creationDate = LocalDateTime.now();

    @NotNull
    private LocalDateTime changeDate = LocalDateTime.now();


    public Task(@NotNull @NotBlank String description) {
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
}
