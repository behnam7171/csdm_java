package com.project.csdm_java.repositories;

import com.project.csdm_java.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
