package com.project.csdm_java.repositories;

import com.project.csdm_java.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
