package com.example.demo.exception;

import com.example.demo.request.MessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<MessageResponse> handelUserNotFoundException(BadRequestException exception, WebRequest request) {
        return getObjectResponseEntity(exception.getLocalizedMessage(), exception.toString(), exception, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<MessageResponse> handelUserNotFoundException(ResourceNotFoundException exception, WebRequest request) {
        return getObjectResponseEntity(exception.getLocalizedMessage(), exception.toString(), exception, HttpStatus.NOT_FOUND);

    }

    private ResponseEntity<MessageResponse> getObjectResponseEntity(String localizedMessage, String s, RuntimeException exception, HttpStatus status) {
        String errorMessageDescription = localizedMessage;
        if (errorMessageDescription == null) errorMessageDescription = s;
        MessageResponse errorMessage = new MessageResponse(errorMessageDescription);
        return new ResponseEntity<MessageResponse>(
                errorMessage, status
        );
    }


}
