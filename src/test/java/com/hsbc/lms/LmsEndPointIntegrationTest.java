package com.hsbc.lms;

import com.hsbc.lms.domain.Book;
import com.hsbc.lms.domain.BookTransaction;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LmsEndPointIntegrationTest {

    private static final Logger LOG = LoggerFactory.getLogger(LmsEndPointIntegrationTest.class);

    @LocalServerPort
    private int port;

    private RestTemplate restTemplate = new RestTemplate();

    @Test
    public void testBookFindById(){

        String url = "http://localhost:" + port + "/book/findById?id=1";

        Book result = this.restTemplate.getForObject(url, Book.class);
        LOG.info("Find by Id for 1 is  {}", result);

        assertThat(result).isNotNull();
        //1,The Very Hungry Catterpillar,Eric Carle
        Book book = new Book();
        book.setId(1l);
        book.setName("The Very Hungry Catterpillar");
        book.setAuthorName("Eric Carle");

        assertThat(result).usingRecursiveComparison().isEqualTo(book);
    }

    @Test
    public void testBookFindByKeyword(){

        String url = "http://localhost:" + port + "/book/find?keyword=Roald";

        List result = this.restTemplate.getForObject(url, List.class);
        LOG.info("Find by keyword 'Roald' result size is {} ",  result.size());

        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(2);
    }

    @Test
    public void testBorrow(){

        String url = "http://localhost:" + port + "/borrow?bookId=4&userId=10";

        BookTransaction result = this.restTemplate.getForObject(url, BookTransaction.class);
        LOG.info("Borrow Book responded with  result {} ",  result);

        assertThat(result).isNotNull();
        assertThat(result.getBookId()).isEqualTo(4l);
        assertThat(result.getUserId()).isEqualTo(10l);

    }

    @Test
    public void testReturnBook(){

        String url = "http://localhost:" + port + "/return?bookId=1";

        String result = this.restTemplate.getForObject(url, String.class);
        LOG.info("Return Book responded with  result {} ",  result);

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo("SUCCESS");

    }

}
