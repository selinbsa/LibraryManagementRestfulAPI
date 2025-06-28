package com.example.LibraryManagementRestfulAPI.service.interfaces;

import com.example.LibraryManagementRestfulAPI.dto.request.BookSaveRequest;
import com.example.LibraryManagementRestfulAPI.dto.response.BookResponse;

import java.util.List;

public interface IBookService {
    BookResponse save(BookSaveRequest request);
    List<BookResponse> getAll();
    BookResponse getById(Long id);
    BookResponse update(Long id, BookSaveRequest request);
    void delete(Long id);
}

