package com.exemple.api.app.controller.response;

import com.exemple.api.domain.entity.Task;
import com.exemple.api.app.controller.link.TaskLink;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.data.web.HateoasPageableHandlerMethodArgumentResolver;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

@Getter
public class TaskResponse extends RepresentationModel<TaskResponse> {

    private String description;
    private Boolean concluded;

    public TaskResponse(Task task) {
        this.description = task.getDescription();
        this.concluded = task.getConcluded();

        this.add(TaskLink.self(task.getId()));
        this.add(this.concluded ? TaskLink.deconclude(task.getId()) : TaskLink.conclude(task.getId()));
        this.add(TaskLink.update(task.getId()));
        this.add(TaskLink.delete(task.getId()));
    }

    public static PagedModel<EntityModel<TaskResponse>> pageToResponseHateoasPage(Page<Task> tasks) {
        HateoasPageableHandlerMethodArgumentResolver resolver = new HateoasPageableHandlerMethodArgumentResolver();
        UriComponents uri = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/tasks")
                .build();
        PagedResourcesAssembler<TaskResponse> pra = new PagedResourcesAssembler<>(resolver, uri);

        return pra.toModel(tasks.map(TaskResponse::new));
    }
}
