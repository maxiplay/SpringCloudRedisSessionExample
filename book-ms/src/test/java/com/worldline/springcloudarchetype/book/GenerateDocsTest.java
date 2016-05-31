package com.worldline.springcloudarchetype.book;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.inject.Inject;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import springfox.documentation.staticdocs.Swagger2MarkupResultHandler;

import com.worldline.springcloudarchetype.BookMSApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BookMSApplication.class)
@WebAppConfiguration
@IntegrationTest
@ActiveProfiles("tests")
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "springfox.documentation.swagger.v2")
public class GenerateDocsTest {

	private static final Logger logger = LoggerFactory
			.getLogger(GenerateDocsTest.class);

	@Inject
	private WebApplicationContext context;

	private MockMvc mockMvc;

	private String path;

	private File projectDir;

	@Before
	public void setup() throws IOException {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();

		ClassPathResource pathfileRes = new ClassPathResource(
				"application.properties");
		projectDir = pathfileRes.getFile().getParentFile().getParentFile()
				.getParentFile();
	}

	@Test
	public void convertSwaggerToAsciiDoc() throws Exception {
		addHeaderForExample();
		Swagger2MarkupResultHandler.Builder builder = Swagger2MarkupResultHandler
				.outputDirectory(outputDirForFormat("asciidoc"))
				.withExamples(
						projectDir + "/target/docs/asciidoc/generated/examples");
		mockMvc.perform(get(path).accept(MediaType.APPLICATION_JSON))
				.andDo(builder.build()).andExpect(status().isOk());
		copyIndex("asciidoc");
	}

	private void addHeaderForExample() {
		try {
			Files.walk(
					new File(projectDir
							+ "/target/docs/asciidoc/generated/examples")
							.toPath())
					.filter(f -> f.getFileName().endsWith("http-response"))
					.forEach(this::writeHeaderForExample);
		} catch (IOException e) {

		}
	}

	private void writeHeaderForExample(Path file) {
		FileWriter writer = null;
		FileReader reader = null;
		try {
			writer = new FileWriter(new File(file.toString() + ".adoc"));
			reader = new FileReader(file.toFile());
			IOUtils.write(
					"[source, json]" + System.getProperty("line.separator"),
					writer);
			IOUtils.copy(reader, writer);
		} catch (IOException e) {

		} finally {
			IOUtils.closeQuietly(writer);
			IOUtils.closeQuietly(reader);
			FileUtils.deleteQuietly(file.toFile());
		}
	}

	private void copyIndex(String format) {
		FileWriter writer = null;
		InputStream inputStream = null;
		try {
			writer = new FileWriter(new File(projectDir, "target/docs/"
					+ format + "/index.adoc"));
			inputStream = Thread.currentThread().getContextClassLoader()
					.getResourceAsStream("asciidoc/index.adoc");
			IOUtils.copy(inputStream, writer);
		} catch (IOException e) {
			logger.error("Generate index.adoc", e);
		} finally {
			IOUtils.closeQuietly(writer);
			IOUtils.closeQuietly(inputStream);
		}
	}

	private String outputDirForFormat(String format) throws IOException {
		return new File(projectDir, "target/docs/" + format + "/generated")
				.getAbsolutePath();
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
