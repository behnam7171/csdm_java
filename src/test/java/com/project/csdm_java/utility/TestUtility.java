package com.project.csdm_java.utility;

import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import io.micrometer.core.instrument.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;

import static org.assertj.core.api.Assertions.assertThat;

public final class TestUtility {
    // final -> prevent extend and inheritance

    static String requestPathTemplate = "book/{0}/{1}.graphql";
    static String resultPathTemplate = "book/{0}/{1}.json";
    @Autowired
    GraphQLTestTemplate testTemplate;

    public static String readFile(String path) throws IOException {
        InputStream fileStream = new ClassPathResource(path).getInputStream();
        return IOUtils.toString(fileStream, StandardCharsets.UTF_8);
    }
}
