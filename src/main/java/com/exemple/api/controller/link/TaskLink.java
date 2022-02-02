package com.exemple.api.controller.link;

import com.exemple.api.controller.TaskController;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.util.UriComponentsBuilder;

public class TaskLink {

    /**
     * Recebe a Id de uma task e retorna um link para o metodo readOne da TaskController, com "Rel = Self".
     *
     * @param id
     * @return Link
     */
    public static Link self(Long id){
            return WebMvcLinkBuilder
                    .linkTo(TaskController.class)
                    .slash(id)
                    .withSelfRel()
                    .withType("get");
    }

    /**
     * Recebe a Id de uma task e retorna um link para o metodo readOne da TaskController.
     *
     * @param id
     * @param rel
     * @return Link
     */
    public static Link readOne(Long id, String rel){
        return WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder
                        .methodOn(TaskController.class)
                        .readOne(id))
                .withRel(rel)
                .withType("get");
    }

    /**
     * Recebe a Id de uma task e retorna um link para o metodo readOne da TaskController.
     *
     * @param id
     * @return Link
     */
    public static Link readOne(Long id){
        return WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder
                        .methodOn(TaskController.class)
                        .readOne(id))
                .withRel("readOneTask")
                .withType("get");
    }

    /**
     * Retorna um link para o metodo readAll da TaskController.
     *
     * @param description
     * @param rel
     * @return Link
     */
    public static Link readAll(String description, String rel){
        return WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder
                        .methodOn(TaskController.class)
                        .readAll(description, null))
                .withRel(rel)
                .withType("get");
    }

    /**
     * Retorna um link para o metodo readAll da TaskController.
     *
     * @param description
     * @param concluded
     * @return Link
     */
    public static Link readAll(String description){
        return WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder
                        .methodOn(TaskController.class)
                        .readAll(description, null))
                .withRel("readAllTasks")
                .withType("get");
    }

    /**
     * Retorna um link para o metodo create da TaskController.
     *
     * @param rel
     * @return Link
     */
    public static Link create(String rel){
        return WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder
                        .methodOn(TaskController.class)
                        .create(null))
                .withRel(rel)
                .withType("post");
    }

    /**
     * Retorna um link para o metodo create da TaskController.
     *
     * @return Link
     */
    public static Link create(){
        return WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder
                        .methodOn(TaskController.class)
                        .create(null))
                .withRel("createTask")
                .withType("post");
    }

    /**
     * Recebe a Id de uma task e retorna um link para o metodo update da TaskController.
     *
     * @param id
     * @param rel
     * @return Link
     */
    public static Link update(Long id, String rel){
        return WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder
                        .methodOn(TaskController.class)
                        .update(id, null))
                .withRel(rel)
                .withType("put");
    }

    /**
     * Recebe a Id de uma task e retorna um link para o metodo update da TaskController.
     *
     * @param id
     * @return Link
     */
    public static Link update(Long id){
        return WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder
                        .methodOn(TaskController.class)
                        .update(id, null))
                .withRel("updateTask")
                .withType("put");
    }

    /**
     * Recebe a Id de uma task e retorna um link para o metodo delete da TaskController.
     *
     * @param id
     * @param rel
     * @return Link
     */
    public static Link delete(Long id, String rel){
        return WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder
                        .methodOn(TaskController.class)
                        .delete(id))
                .withRel(rel)
                .withType("delete");
    }

    /**
     * Recebe a Id de uma task e retorna um link para o metodo delete da TaskController.
     *
     * @param id
     * @return Link
     */
    public static Link delete(Long id){
        return WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder
                        .methodOn(TaskController.class)
                        .delete(id))
                .withRel("deleteTask")
                .withType("delete");
    }

    /**
     * Recebe a Id de uma task e retorna um link para o metodo conclude da TaskController.
     *
     * @param id
     * @param rel
     * @return Link
     */
    public static Link conclude(Long id, String rel){
        return WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder
                        .methodOn(TaskController.class)
                        .conclude(id))
                .withRel(rel)
                .withType("post");
    }

    /**
     * Recebe a Id de uma task e retorna um link para o metodo conclude da TaskController.
     *
     * @param id
     * @return Link
     */
    public static Link conclude(Long id){
        return WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder
                        .methodOn(TaskController.class)
                        .conclude(id))
                .withRel("concludeTask")
                .withType("post");
    }

    /**
     * Recebe a Id de uma task e retorna um link para o metodo deconclude da TaskController.
     *
     * @param id
     * @param rel
     * @return Link
     */
    public static Link deconclude(Long id, String rel) {
        return WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder
                        .methodOn(TaskController.class)
                        .deconclude(id))
                .withRel(rel)
                .withType("delete");
    }

    /**
     * Recebe a Id de uma task e retorna um link para o metodo deconclude da TaskController.
     *
     * @param id
     * @return Link
     */
    public static Link deconclude(Long id) {
        return WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder
                        .methodOn(TaskController.class)
                        .deconclude(id))
                .withRel("deconcludeTask")
                .withType("delete");
    }
}
