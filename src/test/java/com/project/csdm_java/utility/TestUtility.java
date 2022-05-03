package com.project.csdm_java.utility;

import io.micrometer.core.instrument.util.IOUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public final class TestUtility {
    // prevent extend and inheritance

    public static String readFile(String path) throws IOException {
        InputStream fileStream = new ClassPathResource(path).getInputStream();
        return IOUtils.toString(fileStream, StandardCharsets.UTF_8);
    }
}
