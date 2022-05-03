package com.project.csdm_java.author;

import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import com.project.csdm_java.CsdmJavaApplication;
import com.project.csdm_java.models.Author;
import com.project.csdm_java.models.Book;
import com.project.csdm_java.models.Genre;
import com.project.csdm_java.repositories.AuthorRepository;
import com.project.csdm_java.repositories.BookRepository;
import com.project.csdm_java.resolvers.MutationResolver;
import com.project.csdm_java.resolvers.QueryResolver;
import com.project.csdm_java.utility.TestUtility;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AuthorTest {
    @Autowired
    GraphQLTestTemplate testTemplate;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    AuthorRepository authorRepository;
    String requestPathTemplate = "author/{0}/{1}.graphql";
    String resultPathTemplate = "author/{0}/{1}.json";

    @BeforeAll
    public void insert_data_for_test() {

        Author simone = new Author("Simone", "Lunari", LocalDate.of(2020, Month.JANUARY, 8));
        Author behnam = new Author("Behnam", "Bozorgi", LocalDate.of(1992, Month.APRIL, 29));
        List<Author> authors = Arrays.asList(simone, behnam);
        authorRepository.saveAll(authors);

        Book book = new Book("Behnam: Story of my life", Genre.FICTION);
        bookRepository.save(book);
    }

    @Test
    public void create_author() throws IOException, JSONException {
        String testFileName = "create_author";
        String requestFilePath = MessageFormat.format(requestPathTemplate, new String[]{"requests", testFileName});
        String resultFilePath = MessageFormat.format(resultPathTemplate, new String[]{"results", testFileName});

        String expectedResponse = TestUtility.readFile(resultFilePath);

        GraphQLResponse result = testTemplate.postForResource(requestFilePath);


        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertEquals(expectedResponse, result.getRawResponse().getBody(), true);

    }

    @Test
    public void specific_author() throws IOException, JSONException {
        String testFileName = "specific_author";
        String requestFilePath = MessageFormat.format(requestPathTemplate, new String[]{"requests", testFileName});
        String resultFilePath = MessageFormat.format(resultPathTemplate, new String[]{"results", testFileName});

        String expectedResponse = TestUtility.readFile(resultFilePath);

        GraphQLResponse result = testTemplate.postForResource(requestFilePath);


        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertEquals(expectedResponse, result.getRawResponse().getBody(), true);

    }
}
