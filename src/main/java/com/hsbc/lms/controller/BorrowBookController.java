package com.hsbc.lms.controller;

import com.hsbc.lms.domain.BookTransaction;
import com.hsbc.lms.service.BorrowBookService;
import com.hsbc.lms.service.BorrowBookServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static org.springframework.http.HttpStatus.OK;

@RestController
public class BorrowBookController {

    private static final Logger LOG = LoggerFactory.getLogger(BorrowBookController.class);

    @Resource(name = BorrowBookServiceImpl.BEAN_NAME)
    private BorrowBookService borrowBookService;

    @GetMapping("borrow")
    public ResponseEntity<BookTransaction> borrow(@RequestParam long bookId, @RequestParam long userId) {
        LOG.info("Controller - Borrow book {} for user {}", bookId, userId);
        BookTransaction bookTransaction = borrowBookService.borrow(bookId, userId);
        LOG.info("Controller - Borrow book {} for user {} has response ", bookId, userId, bookTransaction);
        return ResponseEntity.status(OK).body(bookTransaction);
    }

    @GetMapping("return")
    public Object returnBook(@RequestParam long bookId) {
        LOG.info("Controller - Return book {} ", bookId);
        borrowBookService.returnBook(bookId);
        LOG.info("Controller - Returned book {} successfully ", bookId);
        return ResponseEntity.status(OK).body("SUCCESS");
    }
}