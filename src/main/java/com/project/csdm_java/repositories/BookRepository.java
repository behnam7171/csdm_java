package com.project.csdm_java.repositories;

import com.project.csdm_java.models.Book;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    @EntityGraph(
            type = EntityGraph.EntityGraphType.FETCH,
            attributePaths = {
                    "authors",
                    "authors.firstName",
                    "authors.lastName",
                    "authors.dateOfBirth"
            }
    )
    List<Book> findAll();

}
