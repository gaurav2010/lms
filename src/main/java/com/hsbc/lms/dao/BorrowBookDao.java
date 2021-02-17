package com.hsbc.lms.dao;

import com.hsbc.lms.domain.BookTransaction;

public interface BorrowBookDao {

    BookTransaction borrow(long bookId, long userId);

    void returnBook(long bookId);
}
