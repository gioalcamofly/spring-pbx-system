package com.example.pbx.handlers;

import com.example.pbx.exception.PbxNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class PbxNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(PbxNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String pbxNotFoundHandler(PbxNotFoundException ex) { return ex.getMessage(); }
}
