package com.tasker.server.boards.rest;

import com.tasker.server.conf.RestTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.org.apache.commons.lang.RandomStringUtils;

import javax.annotation.Resource;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RestTest
public class BoardUpdatingControllerTest {

    @Resource
    private MockMvc mockMvc;

    @Test
    public void it_updates_boards() throws Exception {

        this.mockMvc.perform(
            put("/boards/0e37df36-f698-11e6-8dd4-cb9ced3df976")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"Updated Title\"}")
        ).andExpect(status().isOk());
    }

    @Test
    public void it_returns_error_if_title_is_missing() throws Exception {
        this.mockMvc.perform(
            put("/boards/0e37df36-f698-11e6-8dd4-cb9ced3df976")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}")
        ).andExpect(status().isBadRequest())
            .andExpect(content().json("{\"title\":\"must not be blank\",\"statusCode\":400}", true));
    }

    @Test
    public void it_returns_error_if_title_is_blank() throws Exception {
        this.mockMvc.perform(
            put("/boards/0e37df36-f698-11e6-8dd4-cb9ced3df976")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"\"}")
        ).andExpect(status().isBadRequest())
            .andExpect(content().json("{\"title\":\"must not be blank\",\"statusCode\":400}", true));
    }

    @Test
    public void it_returns_error_if_title_is_too_long() throws Exception {
        String longTitle = RandomStringUtils.randomAlphanumeric(51);

        this.mockMvc.perform(
            put("/boards/0e37df36-f698-11e6-8dd4-cb9ced3df976")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"" + longTitle + "\"}")
        ).andExpect(status().isBadRequest())
            .andExpect(content().json("{\"title\":\"size must be between 0 and 50\",\"statusCode\":400}", true));
    }

    @Test
    public void it_returns_error_if_description_is_too_long() throws Exception {
        String longDescription = RandomStringUtils.randomAlphanumeric(2001);

        this.mockMvc.perform(
            put("/boards/0e37df36-f698-11e6-8dd4-cb9ced3df976")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"Some Title\", \"description\":\"" + longDescription + "\"}")
        ).andExpect(status().isBadRequest())
            .andExpect(content().json("{\"description\":\"size must be between 0 and 2000\",\"statusCode\":400}", true));
    }

    @Test
    public void it_returns_not_found_if_board_does_not_exist() throws Exception {
        String id = UUID.randomUUID().toString();
        this.mockMvc.perform(
            put("/boards/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"Updated Title\"}"))
            .andExpect(status().isNotFound())
            .andExpect(content().json("{\"message\":\"Board not found for id - " + id + "\",\"statusCode\":404}", true));
    }

    @Test
    public void it_returns_error_if_id_is_invalid() throws Exception {
        String id = "blabla";
        this.mockMvc.perform(
            put("/boards/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"Updated Title\"}"))
            .andExpect(status().isBadRequest())
            .andExpect(content().json("{\"message\":\"Invalid UUID string: " + id + "\",\"statusCode\":400}", true));
    }
}
