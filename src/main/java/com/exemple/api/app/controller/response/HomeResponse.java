package com.exemple.api.app.controller.response;

import com.exemple.api.app.controller.link.TaskLink;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

@Getter
public class HomeResponse extends RepresentationModel<HomeResponse> {

    public HomeResponse() {
        this.add(TaskLink.readAll(null));
        this.add(TaskLink.create());
    }
}
