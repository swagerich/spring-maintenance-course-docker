package com.erich.hibernate.handler;

import com.erich.hibernate.exception.NotFoundException;
import com.erich.hibernate.exception.ResourceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class HandlerException {

    @ExceptionHandler(NotFoundException.class)
    public ProblemDetail notFoundExceptionHandler(NotFoundException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problemDetail.setTitle("Not_Found");
        problemDetail.setDetail(e.getMessage());
        problemDetail.setProperty("hora:", LocalDate.now());
        return problemDetail;
    }

    @ExceptionHandler(ResourceException.class)
    public ProblemDetail resourceExceptionHandler(ResourceException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle("Bad-Request");
        problemDetail.setDetail(e.getMessage());
        problemDetail.setProperty("hora:", LocalDate.now());
        return problemDetail;
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail internalServerExceptionHandler(Exception e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        problemDetail.setTitle("SERVER_ERROR");
        problemDetail.setDetail(e.getMessage());
        problemDetail.setProperty("hora:", LocalDate.now());
        return problemDetail;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail methodNotValidHandlerException(MethodArgumentNotValidException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        Map<String, String> mapErrores = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(er -> {
            mapErrores.put(er.getField(), "El campo" + " " + er.getField() + " " + er.getDefaultMessage());
        });
        problemDetail.setTitle("Bad_Request");
        problemDetail.setDetail("");
        problemDetail.setProperty("Hora : ", LocalDate.now());
        problemDetail.setProperty("Uups !!", mapErrores);
        return problemDetail;
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ProblemDetail HandlerAccessDeniedException(AccessDeniedException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.UNAUTHORIZED);
        problemDetail.setTitle("UNAUTHORIZED");
        problemDetail.setDetail(e.getMessage());
        problemDetail.setProperty("Hora", LocalDate.now());
        return problemDetail;
    }
}
