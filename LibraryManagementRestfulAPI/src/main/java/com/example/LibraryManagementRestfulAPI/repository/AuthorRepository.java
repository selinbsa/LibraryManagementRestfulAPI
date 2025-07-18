package com.example.LibraryManagementRestfulAPI.repository;

import com.example.LibraryManagementRestfulAPI.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
