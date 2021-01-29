package com.exemplo.gestao.modelo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

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
    private LocalDateTime creationDate;
    private LocalDateTime changeDate;

    public Task(String description) {
        this.description = description;
        this.changeDate = LocalDateTime.now();
        this.creationDate = LocalDateTime.now();
    }
}
