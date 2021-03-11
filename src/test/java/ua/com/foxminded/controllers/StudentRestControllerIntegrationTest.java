package ua.com.foxminded.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ua.com.foxminded.domain.dto.GroupDTO;
import ua.com.foxminded.domain.dto.StudentDTO;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static sun.plugin2.util.PojoUtil.toJson;


@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@Sql(value = "classpath:schema.sql")

class StudentRestControllerIntegrationTest{

    @Autowired
    private MockMvc mvc;




    @Test
    public void shouldReturnAllStudents() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/api/students"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnExceptionOnBadRequest() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/api/studentshhh"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }


    @Test
    public void shouldReturnFromDbById() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/api/students/2"))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(jsonPath("$.id").value("2"));
    }

    @Test
    public void shouldCreateStudent() throws  Exception{
        StudentDTO newStudent = getNewStudent();

        mvc.perform(MockMvcRequestBuilders.post("/api/students/")
                .content(new ObjectMapper().writeValueAsString(newStudent))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldUpdateStudentWithId() throws Exception{
        StudentDTO newStudent = getNewStudent();
        newStudent.setId(3);
        newStudent.setName("update");

        mvc.perform(MockMvcRequestBuilders.put("/api/students/3")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(newStudent)))
                .andDo(print())
                .andExpect(status().isAccepted());
    }

    @Test
    public void shouldDeleteById() throws Exception{
        mvc.perform(MockMvcRequestBuilders.delete("/api/students/3")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isAccepted());
    }


    private StudentDTO getNewStudent() {
        StudentDTO newStudent = new StudentDTO();
        newStudent.setId(4);
        newStudent.setName("John4");
        newStudent.setSurName("John4");
        newStudent.setCourse(2);
        GroupDTO group = new GroupDTO();
        group.setGroupId(1);
        group.setDescription("fdfdfd");
        group.setAbbreviate("fdfdfd");
        newStudent.setGroup(group);
        return newStudent;
    }


}