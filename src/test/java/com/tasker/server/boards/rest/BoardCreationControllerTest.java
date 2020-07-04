package com.tasker.server.boards.rest;

import com.tasker.server.conf.RestTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.org.apache.commons.lang.RandomStringUtils;

import javax.annotation.Resource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RestTest
public class BoardCreationControllerTest {

    @Resource
    private MockMvc mockMvc;

    @Test
    public void it_creates_boards() throws Exception {
        this.mockMvc.perform(
            post("/boards")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"Some Title\"}")
        ).andExpect(status().isOk());
    }

    @Test
    public void it_returns_error_if_title_is_missing() throws Exception {
        this.mockMvc.perform(
            post("/boards")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}")
        ).andExpect(status().isBadRequest())
            .andExpect(content().json("{\"title\":\"must not be blank\",\"statusCode\":400}"));
    }

    @Test
    public void it_returns_error_if_title_is_blank() throws Exception {
        this.mockMvc.perform(
            post("/boards")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"\"}")
        ).andExpect(status().isBadRequest())
            .andExpect(content().json("{\"title\":\"must not be blank\",\"statusCode\":400}"));
    }

    @Test
    public void it_returns_error_if_title_is_too_long() throws Exception {
        String longTitle = RandomStringUtils.randomAlphanumeric(51);

        this.mockMvc.perform(
            post("/boards")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"" + longTitle + "\"}")
        ).andExpect(status().isBadRequest())
            .andExpect(content().json("{\"title\":\"size must be between 0 and 50\",\"statusCode\":400}"));
    }

    @Test
    public void it_returns_error_if_description_is_too_long() throws Exception {
        String longDescription = RandomStringUtils.randomAlphanumeric(2001);

        this.mockMvc.perform(
            post("/boards")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"Some Title\", \"description\":\"" + longDescription + "\"}")
        ).andExpect(status().isBadRequest())
            .andExpect(content().json("{\"description\":\"size must be between 0 and 2000\",\"statusCode\":400}"));
    }
}
