package com.tasker.server.boards.rest;

import com.tasker.server.conf.RestTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.Resource;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RestTest
public class BoardDeletionControllerTest {

    @Resource
    private MockMvc mockMvc;

    @Test
    public void it_returns404_not_found() throws Exception {
        String id = UUID.randomUUID().toString();
        this.mockMvc.perform(
            delete("/boards/" + id))
            .andExpect(status().isNotFound())
            .andExpect(content().json("{\"message\":\"Board not found for id - " + id + "\",\"statusCode\":404}", true));
    }

    @Test
    public void it_returns_error_if_id_is_invalid() throws Exception {
        String id = "blabla";
        this.mockMvc.perform(
            delete("/boards/" + id))
            .andExpect(status().isBadRequest())
            .andExpect(content().json("{\"message\":\"Invalid UUID string: " + id + "\",\"statusCode\":400}", true));
    }

    @Test
    public void it_successfully_deletes_a_board() throws Exception {
        this.mockMvc.perform(
            delete("/boards/0e37df36-f698-11e6-8dd4-cb9ced3df977"))
            .andExpect(status().isOk());
    }
}
