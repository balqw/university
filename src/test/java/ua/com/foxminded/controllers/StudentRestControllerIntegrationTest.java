package ua.com.foxminded.controllers;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ua.com.foxminded.domain.dto.StudentDTO;
import ua.com.foxminded.restController.StudentController;
import ua.com.foxminded.service.StudentService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@SpringBootTest

class StudentRestControllerIntegrationTest{
    @MockBean
    private StudentService studentService;
    @Autowired
    private StudentController studentController;
    @Autowired
    MockMvc mvc;

    @Test
    public void methodFindByIdShouldReturnEntityWithSameId() throws Exception{
        StudentDTO student = new StudentDTO();
        student.setId(5);
        student.setName("John");
        student.setSurName("Greaves");

        when(studentService.findOne(5)).thenReturn(student);

        mvc.perform(MockMvcRequestBuilders.get("/api/students/5"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("5"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("John"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.surname").value("Greaves"));
    }

}