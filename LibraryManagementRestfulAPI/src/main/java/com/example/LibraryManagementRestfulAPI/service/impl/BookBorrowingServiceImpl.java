package com.example.LibraryManagementRestfulAPI.service.impl;

import com.example.LibraryManagementRestfulAPI.core.exception.NotFoundException;
import com.example.LibraryManagementRestfulAPI.dto.request.BookBorrowingSaveRequest;
import com.example.LibraryManagementRestfulAPI.dto.response.BookBorrowingResponse;
import com.example.LibraryManagementRestfulAPI.entity.Book;
import com.example.LibraryManagementRestfulAPI.entity.BookBorrowing;
import com.example.LibraryManagementRestfulAPI.repository.BookBorrowingRepository;
import com.example.LibraryManagementRestfulAPI.repository.BookRepository;
import com.example.LibraryManagementRestfulAPI.service.interfaces.IBookBorrowingService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookBorrowingServiceImpl implements IBookBorrowingService {

    private final BookRepository bookRepository;
    private final BookBorrowingRepository bookBorrowingRepository;
    private final ModelMapper modelMapper;

    @Override
    public BookBorrowingResponse save(BookBorrowingSaveRequest request) {
        Book book = bookRepository.findById(request.getBookId())
                .orElseThrow(() -> new NotFoundException("Kitap bulunamadı: " + request.getBookId()));

        if (book.getStock() <= 0) {
            throw new RuntimeException("Kitap stokta yok, ödünç verilemez.");
        }

        BookBorrowing borrowing = new BookBorrowing();
        borrowing.setBook(book);
        borrowing.setBorrowerName(request.getBorrowerName());
        borrowing.setBorrowerEmail(request.getBorrowerEmail());
        borrowing.setBorrowingDate(request.getBorrowingDate());
        borrowing.setReturnDate(null);

        book.setStock(book.getStock() - 1);

        bookRepository.save(book);
        return modelMapper.map(bookBorrowingRepository.save(borrowing), BookBorrowingResponse.class);
    }

    @Override
    public List<BookBorrowingResponse> getAll() {
        return bookBorrowingRepository.findAll().stream()
                .map(b -> {
                    BookBorrowingResponse res = modelMapper.map(b, BookBorrowingResponse.class);
                    res.setBookName(b.getBook().getName());
                    return res;
                }).toList();
    }

    @Override
    public BookBorrowingResponse returnBook(Long id, LocalDate returnDate) {
        BookBorrowing borrowing = bookBorrowingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Ödünç alma kaydı bulunamadı: " + id));

        borrowing.setReturnDate(returnDate);

        Book book = borrowing.getBook();
        book.setStock(book.getStock() + 1);

        bookRepository.save(book);
        return modelMapper.map(bookBorrowingRepository.save(borrowing), BookBorrowingResponse.class);
    }
}
