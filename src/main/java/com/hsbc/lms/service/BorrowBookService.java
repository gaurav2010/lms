package com.hsbc.lms.service;

import com.hsbc.lms.domain.BookTransaction;

public interface BorrowBookService {

    BookTransaction borrow(long bookId, long userId);

    void returnBook(long bookId);
}
