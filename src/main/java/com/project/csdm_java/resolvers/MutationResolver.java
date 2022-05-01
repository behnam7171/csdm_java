package com.project.csdm_java.resolvers;

import com.project.csdm_java.models.Author;
import com.project.csdm_java.models.Book;
import com.project.csdm_java.models.Genre;
import com.project.csdm_java.repositories.AuthorRepository;
import com.project.csdm_java.repositories.BookRepository;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

@Component
public class MutationResolver implements GraphQLMutationResolver {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public MutationResolver(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public Book createBook(String title, Genre genre, Set<Author> authors) {
        Book book = new Book();
        book.setTitle(title);
        book.setGenre(genre);
        book.setAuthors(authors);
        bookRepository.save(book);
        return book;
    }

    public boolean deleteBook(Long id) {
        bookRepository.deleteById(id);
        return true;
    }

    public Book updateBook(Long id, String title, Genre genre, Set<Author> authors) throws ObjectNotFoundException {
        Optional<Book> currentBook = bookRepository.findById(id);
        try {
            if (currentBook.isPresent()) {
                Book book = currentBook.get();
                if (title != null) {
                    book.setTitle(title);
                }
                if (genre != null) {
                    book.setGenre(genre);
                }
                if (authors != null) {
                    book.setAuthors(authors);
                }
                bookRepository.save(book);
                return book;
            } else {
                return null;
            }
        } catch (ObjectNotFoundException e) {
            return null;
        }
    }

    public Author createAuthor(String firstName, String lastName, LocalDate dateOfBirth) {
        Author author = new Author();
        author.setFirstName(firstName);
        author.setLastName(lastName);
        author.setDateOfBirth(dateOfBirth);
        authorRepository.save(author);
        return author;
    }
}
