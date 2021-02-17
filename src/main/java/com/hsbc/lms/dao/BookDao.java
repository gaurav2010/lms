package com.hsbc.lms.dao;

import com.hsbc.lms.domain.Book;

import java.util.List;

public interface BookDao {

    List<Book> find(String keyword);

    Book getBookWithId(long id);
}
