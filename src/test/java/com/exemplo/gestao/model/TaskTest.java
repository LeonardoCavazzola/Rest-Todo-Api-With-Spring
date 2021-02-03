package com.exemplo.gestao.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @Test
    void conclude() {

        Task task = new Task("test");
        task.conclude();

        assertTrue(task.getConcluded());
        assertNotNull(task.getConclusionDate());
    }

    @Test
    void deconclude() {

        Task task = new Task("test");
        task.conclude();
        task.deconclude();

        assertFalse(task.getConcluded());
        assertNull(task.getConclusionDate());
    }
}