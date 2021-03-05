package ua.com.foxminded.domain.exceptions;


public class NoDataFoundException extends RuntimeException{
    public NoDataFoundException(String message) {
        super(message);
    }
}
