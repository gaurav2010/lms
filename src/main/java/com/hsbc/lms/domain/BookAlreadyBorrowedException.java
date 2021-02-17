package com.hsbc.lms.domain;

public class BookAlreadyBorrowedException extends RuntimeException{

    public BookAlreadyBorrowedException(String message){

        super(message);
    }
}
