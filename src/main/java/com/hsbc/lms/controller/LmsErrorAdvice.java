package com.hsbc.lms.controller;


import com.hsbc.lms.domain.BookAlreadyBorrowedException;
import com.hsbc.lms.domain.BookNotFoundException;
import com.hsbc.lms.domain.BookReturnException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class LmsErrorAdvice {

    private static final Logger LOG = LoggerFactory.getLogger(LmsErrorAdvice.class);

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<String> handle(RuntimeException re){
        LOG.error("Runtime Error Processing Request ", re);
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(re.getMessage());
    }

    @ExceptionHandler({BookNotFoundException.class})
    public ResponseEntity<String> handle(BookNotFoundException bnfe){
        LOG.error("BookNotFoundException Error Processing Request ", bnfe);
        return ResponseEntity.status(NOT_FOUND).body(bnfe.getMessage());
    }

    @ExceptionHandler({BookReturnException.class})
    public ResponseEntity<String> handle(BookReturnException bre){
        LOG.error("BookNotFoundException Error Processing Request ", bre);
        return ResponseEntity.status(NOT_FOUND).body(bre.getMessage());
    }

    @ExceptionHandler({BookAlreadyBorrowedException.class})
    public ResponseEntity<String> handle(BookAlreadyBorrowedException babe){
        LOG.error("BookNotFoundException Error Processing Request ", babe);
        return ResponseEntity.status(NOT_FOUND).body(babe.getMessage());
    }
}
