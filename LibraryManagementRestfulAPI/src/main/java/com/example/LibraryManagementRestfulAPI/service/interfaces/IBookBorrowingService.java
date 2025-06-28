package com.example.LibraryManagementRestfulAPI.service.interfaces;

import com.example.LibraryManagementRestfulAPI.dto.request.BookBorrowingSaveRequest;
import com.example.LibraryManagementRestfulAPI.dto.response.BookBorrowingResponse;

import java.time.LocalDate;
import java.util.List;

public interface IBookBorrowingService {
    BookBorrowingResponse save(BookBorrowingSaveRequest request);
    List<BookBorrowingResponse> getAll();
    BookBorrowingResponse returnBook(Long id, LocalDate returnDate);
}

