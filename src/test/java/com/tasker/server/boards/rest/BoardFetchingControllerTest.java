package com.tasker.server.boards.rest;

import com.tasker.server.conf.RestTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.Resource;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RestTest
public class BoardFetchingControllerTest {

    @Resource
    private MockMvc mockMvc;

    @Test
    public void it_checks_if_mediatype_is_json() throws Exception {
        String id = UUID.randomUUID().toString();
        this.mockMvc.perform(
            get("/boards"))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void it_returns404_not_found() throws Exception {
        String id = UUID.randomUUID().toString();
        this.mockMvc.perform(
            get("/boards/" + id))
            .andExpect(status().isNotFound())
            .andExpect(content().json("{\"message\":\"Board not found for id - " + id + "\",\"statusCode\":404}", true));
    }

    @Test
    public void it_returns_error_if_id_is_invalid() throws Exception {
        String id = "blabla";
        this.mockMvc.perform(
            get("/boards/" + id))
            .andExpect(status().isBadRequest())
            .andExpect(content().json("{\"message\":\"Invalid UUID string: " + id + "\",\"statusCode\":400}", true));
    }


    @Test
    public void it_successfully_returns_a_board() throws Exception {
        this.mockMvc.perform(
            get("/boards/0e37df36-f698-11e6-8dd4-cb9ced3df976"))
            .andExpect(status().isOk())
            .andExpect(content().json("{\"id\":\"0e37df36-f698-11e6-8dd4-cb9ced3df976\"," +
                "\"title\":\"Some Title\"," +
                "\"createdAt\":\"2020-10-10T00:00:00.000+00:00\"," +
                "\"description\":\"Some Description\"}", true));
    }

    @Test
    public void it_successfully_returns_all_boards() throws Exception {
        this.mockMvc.perform(
            get("/boards"))
            .andExpect(status().isOk())
            .andExpect(content().json(
                "[" +
                    "{" +
                    "\"id\":\"0e37df36-f698-11e6-8dd4-cb9ced3df976\"," +
                    "\"title\":\"Some Title\"," +
                    "\"createdAt\":\"2020-10-10T00:00:00.000+00:00\"," +
                    "\"description\":\"Some Description\"" +
                    "}," +
                    "{" +
                    "\"id\":\"0e37df36-f698-11e6-8dd4-cb9ced3df977\"," +
                    "\"title\":\"Some Title 2\"," +
                    "\"createdAt\":\"2020-07-11T00:00:00.000+00:00\"," +
                    "\"description\":\"Some Description 2\"" +
                    "}" +
                    "]", true));
    }

}
