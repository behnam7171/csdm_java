package com.project.csdm_java.book;

import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.text.MessageFormat;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BookExceptionsTest {
    @Autowired
    GraphQLTestTemplate testTemplate;

    String requestPathTemplate = "book/{0}/{1}.graphql";

    String exceptionBookNotFoundText = "Book was not found!";

    @Test
    public void update_book_not_found() throws IOException, JSONException {
        String testFileName = "update_book_not_found";
        String requestFilePath = MessageFormat.format(requestPathTemplate, new String[]{"requests", testFileName});
        // "Book was not found!"

        GraphQLResponse result = testTemplate.postForResource(requestFilePath);


        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertTrue(result.getRawResponse().getBody().contains(exceptionBookNotFoundText));
    }

    @Test
    public void delete_book_not_found() throws IOException, JSONException {
        String testFileName = "delete_book_not_found";
        String requestFilePath = MessageFormat.format(requestPathTemplate, new String[]{"requests", testFileName});
        // "Book was not found!"

        GraphQLResponse result = testTemplate.postForResource(requestFilePath);


        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertTrue(result.getRawResponse().getBody().contains(exceptionBookNotFoundText));
    }

    @Test
    public void specific_book_not_found() throws IOException, JSONException {
        String testFileName = "specific_book_not_found";
        String requestFilePath = MessageFormat.format(requestPathTemplate, new String[]{"requests", testFileName});
        // "Book was not found!"

        GraphQLResponse result = testTemplate.postForResource(requestFilePath);


        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertTrue(result.getRawResponse().getBody().contains(exceptionBookNotFoundText));
    }

}
