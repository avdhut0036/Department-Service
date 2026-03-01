package com.app.springboot.controller;

import com.app.springboot.service.DepartmentService;
import com.app.springboot.DepartmentMockData;
import com.app.springboot.entity.Department;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest
class DepartmentControllerTest extends DepartmentMockData {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DepartmentController departmentController;

    @MockitoBean
    private DepartmentService departmentService;


    @Test
    void givenValidDepartmentWhenSaveDepartmentThenNoError() throws Exception {

        Mockito.when(departmentService.saveDepartment(ArgumentMatchers.any(Department.class)))
                        .thenReturn(getDepartment());

        mockMvc.perform(MockMvcRequestBuilders.post("/departments/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"departmentName\":\"IT\",\n" +
                                "    \"departmentAddress\":\"Banglore\",\n" +
                                "    \"departmentCode\":\"IT-09\"\n" +
                                "}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.
                        jsonPath("$.departmentName").value("IT"));

    }

    @Test
    void givenInvalidDepartmentWhenSaveDepartmentThenExceptionThrown() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/departments/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("\n" +
                        "    \"departmentAddress\":\"Banglore\",\n" +
                        "    \"departmentCode\":\"IT-09\""))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }


}