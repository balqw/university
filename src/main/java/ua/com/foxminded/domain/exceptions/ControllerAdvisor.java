package ua.com.foxminded.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;


@ControllerAdvice
public class ControllerAdvisor  {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    ResponseEntity<ApiDataException> handleNotFoundException(NotFoundException ex){
        ApiDataException apiDataEx = new ApiDataException(ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(apiDataEx,HttpStatus.NOT_FOUND);
    }

}
