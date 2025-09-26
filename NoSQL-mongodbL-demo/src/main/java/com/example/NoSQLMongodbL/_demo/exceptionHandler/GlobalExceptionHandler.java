package com.example.NoSQLMongodbL._demo.exceptionHandler;

import com.example.NoSQLMongodbL._demo.controller.UserController;
import com.example.NoSQLMongodbL._demo.exceptions.UserException;
import com.example.NoSQLMongodbL._demo.model.ErrorDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GlobalExceptionHandler {
    private static final Logger logger = LogManager.getLogger(UserController.class);

    @ExceptionHandler(UserException.class)
    public ResponseEntity<String> handlerAppException(UserException ex) throws Exception {
        logger.error(String.format("message: %s | stackTrace: %s", ex.getMessage(), ex.getStackTrace().toString()));

        ObjectMapper mapper = new ObjectMapper();

        String responseJson = mapper.writeValueAsString(new ErrorDto(ex.getMessage(), 400));
        return ResponseEntity.badRequest().body(responseJson);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handlerGlobalException(Exception ex) throws Exception {
        logger.error(String.format("message: %s | stackTrace: %s", ex.getMessage(), ex.getStackTrace().toString()));

        ObjectMapper mapper = new ObjectMapper();

        String responseJson = mapper.writeValueAsString(new ErrorDto("Что то пошло не так.", 500));
        return ResponseEntity.status(HttpStatusCode.valueOf(500)).body(responseJson);

    }

}
