package com.hsbc.lms.service;

import com.hsbc.lms.dao.BookDao;
import com.hsbc.lms.dao.BookDaoCsvImpl;
import com.hsbc.lms.domain.Book;
import com.hsbc.lms.domain.BookNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service(BookServiceImpl.BEAN_NAME)
public class BookServiceImpl implements BookService{

    private static final Logger LOG = LoggerFactory.getLogger(BookServiceImpl.class);

    public static final String BEAN_NAME = "com.hsbc.lms.service.BookServiceImpl";

    @Resource(name = BookDaoCsvImpl.BEAN_NAME)
    private BookDao bookDao;

    @Override
    public List<Book> find(String keyword) {
        List<Book> books = bookDao.find(keyword);
        LOG.info("Count of Books received for keyword {} is {}", keyword, books.size());
        if(books.size() == 0){
            throw new BookNotFoundException(keyword);
        }
        return books;
    }

    @Override
    public Book getBookWithId(long id) {
        Book book = bookDao.getBookWithId(id);
        if(book == null){
            throw new BookNotFoundException(Long.toString(id));
        }
        LOG.info("The Book received for id {} is {}", id, book);
        return book;
    }
}
