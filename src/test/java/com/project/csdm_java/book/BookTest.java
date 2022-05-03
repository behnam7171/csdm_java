package com.project.csdm_java.book;

import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import com.project.csdm_java.models.Author;
import com.project.csdm_java.models.Book;
import com.project.csdm_java.models.Genre;
import com.project.csdm_java.repositories.AuthorRepository;
import com.project.csdm_java.repositories.BookRepository;
import com.project.csdm_java.utility.TestUtility;
import org.json.JSONException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.event.annotation.AfterTestClass;

import java.io.IOException;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookTest {
    @Autowired
    GraphQLTestTemplate testTemplate;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    AuthorRepository authorRepository;
    String requestPathTemplate = "book/{0}/{1}.graphql";
    String resultPathTemplate = "book/{0}/{1}.json";

    @BeforeAll
    public void insert_data_for_test() {

        Author simone = new Author("Simone", "Lunari", LocalDate.of(2020, Month.JANUARY, 8));
        Author behnam = new Author("Behnam", "Bozorgi", LocalDate.of(1992, Month.APRIL, 29));
        Author user = new Author("Name", "Family", LocalDate.of(1996, Month.SEPTEMBER, 30));
        List<Author> authors = Arrays.asList(simone, behnam, user);
        authorRepository.saveAll(authors);

        Book book = new Book("Behnam: Story of my life", Genre.FICTION);
        bookRepository.save(book);

        book.addAuthor(simone);
        book.addAuthor(behnam);
        bookRepository.save(book);

        Book book2 = new Book("Behnam: I have 1 author", Genre.NONFICTION);
        bookRepository.save(book2);
        book2.addAuthor(behnam);
        bookRepository.save(book2);
    }

    @Order(1)
    @Test
    public void all_books() throws IOException, JSONException {
        String testFileName = "all_books";
        jsonComparison(testFileName);
    }

    @Test
    public void create_book_with_authors() throws IOException, JSONException {
        String testFileName = "create_book";
        jsonComparison(testFileName);
    }

    @Test
    public void specific_book() throws IOException, JSONException {
        String testFileName = "specific_book";
        jsonComparison(testFileName);
    }

    @Test
    public void update_book() throws IOException, JSONException {
        String testFileName = "update_book";
        jsonComparison(testFileName);
    }

    @Test
    @Order(Integer.MAX_VALUE)
    public void delete_book() throws IOException, JSONException {
        String testFileName = "delete_book";
        jsonComparison(testFileName);
    }

    public void jsonComparison(String testFileName) throws IOException, JSONException {
        String requestFilePath = MessageFormat.format(requestPathTemplate, new String[]{"requests", testFileName});
        String resultFilePath = MessageFormat.format(resultPathTemplate, new String[]{"results", testFileName});

        String expectedResponse = TestUtility.readFile(resultFilePath);

        GraphQLResponse result = testTemplate.postForResource(requestFilePath);


        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertEquals(expectedResponse, result.getRawResponse().getBody(), true);


    }

}
