package com.hsbc.lms.service;

import com.hsbc.lms.domain.Book;

import java.util.List;

public interface BookService {

    List<Book> find(String keyword);

    Book getBookWithId(long id);

}
