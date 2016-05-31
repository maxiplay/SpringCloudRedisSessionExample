package com.worldline.springcloudarchetype;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Book tests
 * 
 * @author A525125
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("tests")
@WebIntegrationTest({ "server.port:0", "eureka.client.enabled:false" })
public abstract class AbstractTest {

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private MongoTemplate mongoTemplate;

	protected MockMvc mockMvc;

	@Before
	public void setup() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
				.build();
	}

	/**
	 * Import Json in Mongo
	 * 
	 * @param collection
	 * @param file
	 */
	protected void importJSON(String collection, String file) {
		try {
			for (Object line : FileUtils.readLines(new File(file), "utf8")) {
				mongoTemplate.save(line, collection);
			}
		} catch (IOException e) {
			throw new RuntimeException("Could not import file: " + file, e);
		}
	}
}
