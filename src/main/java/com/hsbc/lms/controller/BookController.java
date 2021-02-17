package com.hsbc.lms.controller;

import com.hsbc.lms.domain.Book;
import com.hsbc.lms.service.BookService;
import com.hsbc.lms.service.BookServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("book")
public class BookController {

    private static final Logger LOG = LoggerFactory.getLogger(BookController.class);

    @Resource(name = BookServiceImpl.BEAN_NAME)
    BookService bookService;

    @GetMapping("find")
    public ResponseEntity<List<Book>> find(@RequestParam String keyword){
        LOG.info("Controller - find book with keyword {}", keyword);
        List<Book> books = bookService.find(keyword);
        LOG.info("Number of Books returned for keyword {} is {}", keyword, books.size());
        return ResponseEntity.status(OK).body(books);
    }

    @GetMapping("findById")
    public ResponseEntity<Book> getBookWithId(@RequestParam long id){
        LOG.info("Controller - find book with id {}", id);
        Book book = bookService.getBookWithId(id);
        LOG.info("The Books returned for id {} is {}", id, book);
        return ResponseEntity.status(OK).body(book);
    }



}
