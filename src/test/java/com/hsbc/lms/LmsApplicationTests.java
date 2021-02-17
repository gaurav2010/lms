package com.hsbc.lms;

import com.hsbc.lms.controller.BookController;
import com.hsbc.lms.controller.BorrowBookController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class LmsApplicationTests {

	@Autowired
	private BookController bookController;

	@Autowired
	private BorrowBookController borrowBookController;


	@Test
	void contextLoads() {
		assertThat(bookController).isNotNull();
		assertThat(borrowBookController).isNotNull();
	}

}
