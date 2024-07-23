package com.prep.spring_boot_v1;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prep.spring_boot_v1.dto.TaskDTO;

import jakarta.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


import static org.hamcrest.Matchers.hasSize;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = "/cleanup.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
// @DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testPostTask() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(
                post("/create")
                        .contentType("application/json")
                        .param("name", "newTask"))
                .andDo(print())
                .andExpect(jsonPath("$.*", hasSize(2)))
                .andExpect(jsonPath("$.name").value("newTask"))
                .andReturn().getResponse();

        MockHttpServletResponse getAllResponse = mockMvc.perform(
                get("/getall")
                        .contentType("application/json"))
                .andDo(print())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name").value("newTask"))
                .andReturn().getResponse();

        MockHttpServletResponse updatedResponse = mockMvc.perform(
                put("/update")
                        .contentType("application/json")
                        .param("id", "1")
                        .param("newName", "updatedTask"))
                .andDo(print())
                .andExpect(jsonPath("$.*", hasSize(2)))
                .andExpect(jsonPath("$.name").value("updatedTask"))
                .andReturn().getResponse();

        MockHttpServletResponse deletedResponse = mockMvc.perform(
                delete("/delete")
                        .contentType("application/json")
                        .param("id", "1"))
                .andDo(print())
                .andExpect(jsonPath("$").value("Task " + "updatedTask" + " deleted"))
                .andReturn().getResponse();
    }

    @Test
    public void testPostObject() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        TaskDTO task = new TaskDTO("Task as object");

        MockHttpServletResponse response = mockMvc.perform(
                post("/create-object")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(task)))
                .andDo(print())
                .andExpect(jsonPath("$.*", hasSize(2)))
                .andExpect(jsonPath("$.name").value("Task as object"))
                .andReturn().getResponse();
    }



}
