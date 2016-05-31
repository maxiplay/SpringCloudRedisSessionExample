package com.worldline.springcloudarchetype.book;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.security.test.context.support.WithMockUser;

import com.worldline.springcloudarchetype.AbstractTest;
import com.worldline.springcloudarchetype.BookMSApplication;

/**
 * Book tests
 * 
 * @author A525125
 *
 */
@SpringApplicationConfiguration(classes = BookMSApplication.class)
@WithMockUser(password = "maxmax", username = "max3")
public class BookTest extends AbstractTest {

	@Test
	public void getBooksByUser() throws Exception {
		mockMvc.perform(get("/book/max3"))
				.andExpect(status().isOk())
				.andExpect(
						jsonPath("$.content[0].title", is("La princesse Manon")));
	}

	@Override
	@Before
	public void setup() throws Exception {
		super.setup();
		importJSON("user", "src/test/resources/data/user.json");
		importJSON("book", "src/test/resources/data/book.json");
	}
}
