package com.exemplo.gestao.controller.dto;

import com.exemplo.gestao.controller.link.TaskLink;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

@Getter
public class HomeDto extends RepresentationModel<TaskDetailsDto> {

    public HomeDto() {
        super.add(
                TaskLink.readAll(null, null, null),
                TaskLink.create()
        );
    }
}
