package com.project.csdm_java;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import graphql.kickstart.tools.SchemaParserOptions;
import graphql.scalars.ExtendedScalars;
import graphql.schema.idl.RuntimeWiring;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CsdmJavaApplication {

	public static void main(String[] args) {
		RuntimeWiring.newRuntimeWiring().scalar(ExtendedScalars.Date).build();
		SpringApplication.run(CsdmJavaApplication.class, args);
	}

}
