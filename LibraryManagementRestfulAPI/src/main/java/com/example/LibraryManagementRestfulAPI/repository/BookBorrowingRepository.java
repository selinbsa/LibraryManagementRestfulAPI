package com.example.LibraryManagementRestfulAPI.repository;

import com.example.LibraryManagementRestfulAPI.entity.BookBorrowing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookBorrowingRepository extends JpaRepository<BookBorrowing, Long> {
}

