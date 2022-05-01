package com.project.csdm_java.resolvers;

import com.project.csdm_java.models.Author;
import com.project.csdm_java.models.Book;
import com.project.csdm_java.repositories.AuthorRepository;
import com.project.csdm_java.repositories.BookRepository;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QueryResolver implements GraphQLQueryResolver {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    @Autowired
    public QueryResolver(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public Iterable<Book> allBooks() {
        return bookRepository.findAll();
    }

    public Author author(Long id) {
        return authorRepository.findById(id).orElseThrow(null);
    }

    public Book book(Long id) {
        return bookRepository.findById(id).orElseThrow(null);
    }

}