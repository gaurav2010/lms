package com.hsbc.lms.dao;

import com.hsbc.lms.domain.BookAlreadyBorrowedException;
import com.hsbc.lms.domain.BookReturnException;
import com.hsbc.lms.domain.BookTransaction;
import com.hsbc.lms.util.CalculateDueDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static com.hsbc.lms.util.CalculateDueDate.calculateDueDate;

@Service(BorrowBookDaoCsvImpl.BEAN_NAME)
public class BorrowBookDaoCsvImpl implements BorrowBookDao {

    public static final String BEAN_NAME = "com.hsbc.lms.dao.BorrowBookDaoCsvImpl";

    private static final Logger LOG = LoggerFactory.getLogger(BorrowBookDaoCsvImpl.class);


    private static final List<BookTransaction> bookTransactions = new ArrayList<>();
    static{
        try {
            InputStream io = BookDaoCsvImpl.class.getClassLoader().getResourceAsStream("book-transaction.csv");
            BufferedReader br = new BufferedReader(new InputStreamReader(io, "UTF-8"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                BookTransaction bookTransaction = new BookTransaction();
                bookTransaction.setBookId(Long.parseLong(values[0].trim()));
                bookTransaction.setUserId(Long.parseLong(values[1].trim()));
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                bookTransaction.setDueDate(LocalDate.parse(values[2].trim(), formatter));
                BorrowBookDaoCsvImpl.bookTransactions.add(bookTransaction);
            }

        }catch(Throwable th){
            LOG.error("Exception Loading CSV file",th);
        }
    }


    @Override
    public BookTransaction borrow(long bookId, long userId) {
        BookTransaction fetchedValue = BorrowBookDaoCsvImpl.bookTransactions.stream().filter(o -> o.getBookId() == bookId).findFirst().orElse(null);
        if(fetchedValue == null){
            BookTransaction bookTransaction = new BookTransaction();
            bookTransaction.setBookId(bookId);
            bookTransaction.setUserId(userId);
            bookTransaction.setDueDate(calculateDueDate(LocalDate.now()));
            BorrowBookDaoCsvImpl.bookTransactions.add(bookTransaction);
            return bookTransaction;
        }else{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            throw new BookAlreadyBorrowedException("The Book is borrowed and will be returned on " + fetchedValue.getDueDate().format(formatter));
        }
    }

    @Override
    public void returnBook(long bookId) {
        BookTransaction fetchedValue = BorrowBookDaoCsvImpl.bookTransactions.stream().filter(o -> o.getBookId() == bookId).findFirst().orElseGet(null);
        if (fetchedValue != null) {
            BorrowBookDaoCsvImpl.bookTransactions.remove(fetchedValue);
        }else{
            throw new BookReturnException("Could not return book {}" + bookId);
        }
    }
}
