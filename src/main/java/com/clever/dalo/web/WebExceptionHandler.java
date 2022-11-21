package com.clever.dalo.web;

import com.clever.dalo.patient.exception.PatientNotFoundException;
import com.clever.dalo.user.exception.UserAlreadyExistsException;
import com.clever.dalo.user.exception.UserNotFoundException;
import com.clever.dalo.web.dto.ResponseErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class WebExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ResponseErrorDto> handleUserNotFoundException(UserNotFoundException ex) {

        return new ResponseEntity<>(
                new ResponseErrorDto("User with id: " + ex.getId() + " not found"),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ResponseErrorDto> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {

        return new ResponseEntity<>(
                new ResponseErrorDto("User with login: " + ex.getLogin() + " already exists"),
                HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler(PatientNotFoundException.class)
    public ResponseEntity<ResponseErrorDto> handlePatientNotFoundException(PatientNotFoundException ex) {

        return new ResponseEntity<>(
                new ResponseErrorDto("Patient with id: " + ex.getId() + " not found"),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ResponseErrorDto> handleRuntimeException(RuntimeException ex) {

        return new ResponseEntity<>(
                new ResponseErrorDto("Unexpected message"),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
