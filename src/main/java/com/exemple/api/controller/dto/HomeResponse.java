package com.exemple.api.controller.dto;

import com.exemple.api.controller.link.TaskLink;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

@Getter
public class HomeResponse extends RepresentationModel<HomeResponse> {

    public HomeResponse() {
        this.add(TaskLink.readAll(null));
        this.add(TaskLink.create());
    }
}
