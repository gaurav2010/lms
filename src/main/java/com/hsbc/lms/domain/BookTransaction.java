package com.hsbc.lms.domain;

import java.time.LocalDate;
import java.util.Objects;

public class BookTransaction {

    private long bookId;

    private long userId;

    private LocalDate dueDate;

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookTransaction that = (BookTransaction) o;
        return bookId == that.bookId && userId == that.userId && Objects.equals(dueDate, that.dueDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, userId, dueDate);
    }

    @Override
    public String toString() {
        return "BookTransaction{" +
                "bookId=" + bookId +
                ", userId=" + userId +
                ", dueDate=" + dueDate +
                '}';
    }
}
