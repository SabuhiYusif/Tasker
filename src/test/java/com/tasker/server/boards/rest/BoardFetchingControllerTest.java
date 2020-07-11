package com.tasker.server.boards.rest;

import com.tasker.server.boards.Board;
import com.tasker.server.conf.RestTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import org.testcontainers.shaded.org.apache.commons.lang.RandomStringUtils;

import javax.annotation.Resource;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RestTest
public class BoardFetchingControllerTest {

    @Resource
    private MockMvc mockMvc;
    private Board board;

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
    public void it_returns400_bad_request() throws Exception {
        String id = "blabla";
        this.mockMvc.perform(
                get("/boards/" + id))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{\"message\":\"Invalid UUID string: " + id + "\",\"statusCode\":400}", true));
    }


    @Test
    public void it_checks_response_payload() throws Exception {
        this.mockMvc.perform(
                get("/boards/0e37df36-f698-11e6-8dd4-cb9ced3df976" ))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":\"0e37df36-f698-11e6-8dd4-cb9ced3df976\"," +
                                                      "\"title\":\"Some Title\"," +
                                                       "\"createdAt\":\"2020-10-10T00:00:00.000+00:00\"," +
                                                        "\"description\":\"Some Description\"}", true));
    }

}
