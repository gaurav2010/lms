package com.hsbc.lms.service;

import com.hsbc.lms.dao.BorrowBookDaoCsvImpl;
import com.hsbc.lms.domain.BookTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service(BorrowBookServiceImpl.BEAN_NAME)
public class BorrowBookServiceImpl implements BorrowBookService {

    private static final Logger LOG = LoggerFactory.getLogger(BorrowBookServiceImpl.class);

    public static final String BEAN_NAME = "com.hsbc.lms.service.BorrowBookServiceImpl";

    @Resource(name = BorrowBookDaoCsvImpl.BEAN_NAME)
    private BorrowBookDaoCsvImpl borrowBookDaoCsv;


    @Override
    public BookTransaction borrow(long bookId, long userId) {
        return borrowBookDaoCsv.borrow(bookId, userId);
    }

    @Override
    public void returnBook(long bookId) {
        borrowBookDaoCsv.returnBook(bookId);
    }
}
