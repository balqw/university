package ua.com.foxminded.domain.exceptions;


public class NotFoundException extends RuntimeException {
    public NotFoundException(String msg) {
        super(msg);
    }
}
