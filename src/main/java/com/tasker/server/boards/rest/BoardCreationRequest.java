package com.tasker.server.boards.rest;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class BoardCreationRequest {

    @NotBlank
    @Size(max = 50)
    private String title;

    @Size(max = 2000)
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
