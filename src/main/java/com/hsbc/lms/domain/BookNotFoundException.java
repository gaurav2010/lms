package com.hsbc.lms.domain;

public class BookNotFoundException extends RuntimeException{

    public BookNotFoundException(String identifier){
        super("No Book found for keyword {} " + identifier);
    }
}
