package ua.com.foxminded;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ua.com.foxminded.restController.StudentController;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MainSystemTest {

    @Autowired
    private StudentController controller;

    @Test
    public void contextLoad(){

    }

    @Test
    public void controllerShouldBeNotNull(){
        assertThat(controller).isNotNull();
    }


}
