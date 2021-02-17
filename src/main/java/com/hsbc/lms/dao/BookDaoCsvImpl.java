package com.hsbc.lms.dao;

import com.hsbc.lms.domain.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service(BookDaoCsvImpl.BEAN_NAME)
public class BookDaoCsvImpl implements BookDao {

    public static final String BEAN_NAME = "com.hsbc.lms.dao.BookDaoCsvImpl";

    private static final Logger LOG = LoggerFactory.getLogger(BookDaoCsvImpl.class);

    private static final List<Book> books = new ArrayList<>();
    static{
        try {
            InputStream io = BookDaoCsvImpl.class.getClassLoader().getResourceAsStream("book.csv");
            BufferedReader br = new BufferedReader(new InputStreamReader(io, "UTF-8"));
                String line;
                while ((line = br.readLine()) != null) {
                    String[] values = line.split(",");
                    Book book = new Book();
                    book.setId(Long.parseLong(values[0].trim()));
                    book.setName(values[1].trim());
                    book.setAuthorName(values[2].trim());
                    BookDaoCsvImpl.books.add(book);
                }

        }catch(Throwable th){
            LOG.error("Exception Loading CSV file",th);
        }
    }

    @Override
    public List<Book> find(String keyword) {
        return BookDaoCsvImpl.books.stream().filter(o -> o.getAuthorName().toLowerCase().contains(keyword.toLowerCase()) || o.getName().toLowerCase().contains(keyword.toLowerCase())).collect(Collectors.toList());
    }

    @Override
    public Book getBookWithId(long id) {

        return BookDaoCsvImpl.books.stream().filter(o -> o.getId() == id).findFirst().orElseGet(null);
    }

}
