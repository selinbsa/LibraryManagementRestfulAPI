package com.example.LibraryManagementRestfulAPI.controller;

import com.example.LibraryManagementRestfulAPI.dto.request.BookBorrowingSaveRequest;
import com.example.LibraryManagementRestfulAPI.dto.response.BookBorrowingResponse;
import com.example.LibraryManagementRestfulAPI.service.interfaces.IBookBorrowingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/borrowings")
@RequiredArgsConstructor
public class BookBorrowingController {

    private final IBookBorrowingService bookBorrowingService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookBorrowingResponse save(@RequestBody @Valid BookBorrowingSaveRequest request) {
        return bookBorrowingService.save(request);
    }

    @GetMapping
    public List<BookBorrowingResponse> getAll() {
        return bookBorrowingService.getAll();
    }

    @PutMapping("/return/{id}")
    public BookBorrowingResponse returnBook(@PathVariable Long id, @RequestParam("returnDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate returnDate) {
        return bookBorrowingService.returnBook(id, returnDate);
    }
}

