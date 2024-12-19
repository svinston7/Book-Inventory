package com.exception;

import com.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;


import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    private Map<String, Object> buildResponse(String message, HttpStatus status) {
        Map<String, Object> response = new HashMap<>();
        
        response.put("status", status.value());
      
        response.put("message", message);
        return response;
    }

 //  HTTP 200 
    @ExceptionHandler(SuccessException.class)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> handleSuccess(SuccessException ex) {
        return new ResponseEntity<>(buildResponse(ex.getMessage(), HttpStatus.OK), HttpStatus.OK);
    }
 // HTTP 201 
    @ExceptionHandler(CreationException.class)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> handleCreation(CreationException ex) {
        return new ResponseEntity<>(buildResponse(ex.getMessage(), HttpStatus.CREATED), HttpStatus.CREATED);
    }
    // HTTP 202 
    @ExceptionHandler(AcceptedException.class)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> handleAccepted(AcceptedException ex) {
        return new ResponseEntity<>(buildResponse(ex.getMessage(), HttpStatus.ACCEPTED), HttpStatus.ACCEPTED);
    }

     
    //HTTP 404 
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<?> handleResourceNotFound(ResourceNotFoundException ex) {
        return new ResponseEntity<>(buildResponse(ex.getMessage(), HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
    }

    //HTTP 400 
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handleBadRequest(BadRequestException ex) {
        return new ResponseEntity<>(buildResponse(ex.getMessage(), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }

    // HTTP 401 
    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<?> handleUnauthorized(UnauthorizedException ex) {
        return new ResponseEntity<>(buildResponse(ex.getMessage(), HttpStatus.UNAUTHORIZED), HttpStatus.UNAUTHORIZED);
    }

    // HTTP 403 
    @ExceptionHandler(SecurityException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<?> handleForbidden(SecurityException ex) {
        return new ResponseEntity<>(buildResponse(ex.getMessage(), HttpStatus.FORBIDDEN), HttpStatus.FORBIDDEN);
    }

    // HTTP 500 
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<?> handleGlobalException(Exception ex) {
        return new ResponseEntity<>(buildResponse(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

