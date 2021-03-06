package com.project.csdm_java;

import com.project.csdm_java.models.Author;
import com.project.csdm_java.models.Book;
import com.project.csdm_java.models.Genre;
import com.project.csdm_java.repositories.AuthorRepository;
import com.project.csdm_java.repositories.BookRepository;
import com.project.csdm_java.resolvers.MutationResolver;
import com.project.csdm_java.resolvers.QueryResolver;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class CsdmJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CsdmJavaApplication.class, args);
	}

	@Bean
	public QueryResolver query(BookRepository bookRepository, AuthorRepository authorRepository) {
		return new QueryResolver(bookRepository, authorRepository);
	}

	@Bean
	public MutationResolver mutation(BookRepository bookRepository, AuthorRepository authorRepository) {
		return new MutationResolver(bookRepository, authorRepository);
	}


}
