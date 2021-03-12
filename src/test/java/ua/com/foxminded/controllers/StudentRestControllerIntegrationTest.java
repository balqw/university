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

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@Sql(value = "classpath:schema.sql")

class StudentRestControllerIntegrationTest{

    @Autowired
    private MockMvc mvc;

    @Test
    public void shouldReturnAllStudents() throws Exception{
        String json = new ObjectMapper().writeValueAsString(getStudents());
        mvc.perform(MockMvcRequestBuilders.get("/api/students").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(3))
                .andExpect(content().json(json));
    }

    @Test
    public void shouldReturnExceptionOnBadRequest() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/api/incorrect"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void shouldReturnFromDbById() throws Exception{
        String jsonBody = new ObjectMapper().writeValueAsString(getStudent(2,"John2","John2",2,2,"desc2","abbr2"));
        mvc.perform(MockMvcRequestBuilders.get("/api/students/2"))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(content().json(jsonBody));
    }

    @Test
    public void shouldCreateStudent() throws  Exception{
        StudentDTO newStudent = getStudent(4,"John4","John4",2,1,"desc4","abbr4");
        String jsonBody = new ObjectMapper().writeValueAsString(newStudent);
        mvc.perform(MockMvcRequestBuilders.post("/api/students/")
                .content(new ObjectMapper().writeValueAsString(newStudent))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(jsonBody));
    }

    @Test
    public void shouldUpdateStudentWithId() throws Exception{
        StudentDTO newStudent = getStudent(3,"update","John4",2,1,"desc4","abbr4");
        String jsonBody = new ObjectMapper().writeValueAsString(newStudent);
        mvc.perform(MockMvcRequestBuilders.put("/api/students/3")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(newStudent)))
                .andDo(print())
                .andExpect(status().isAccepted())
                .andExpect(content().json(jsonBody));
    }

    @Test
    public void shouldDeleteById() throws Exception{
        mvc.perform(MockMvcRequestBuilders.delete("/api/students/3")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isAccepted());
    }

    private StudentDTO getStudent(int idStudent , String name, String surname, int course, int groupId, String groupDesc, String groupAbbr) {
        StudentDTO newStudent = new StudentDTO();
        newStudent.setId(idStudent);
        newStudent.setName(name);
        newStudent.setSurName(surname);
        newStudent.setCourse(course);
        GroupDTO group = new GroupDTO();
        group.setGroupId(groupId);
        group.setDescription(groupDesc);
        group.setAbbreviate(groupAbbr);
        newStudent.setGroup(group);
        return newStudent;
    }

    private List<StudentDTO> getStudents() {
        List<StudentDTO> students = new ArrayList<>(3);
        students.add(getStudent(1,"John1","John1",1,1,"desc1","abbr1"));
        students.add(getStudent(2,"John2","John2",2,2,"desc2","abbr2"));
        students.add(getStudent(3,"John3","John3",3,3,"desc3","abbr3"));
        return students;
    }
}