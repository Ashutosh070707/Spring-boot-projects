package com.example.smartLibrary.service.impl;

import org.springframework.stereotype.Service;

import com.example.smartLibrary.dto.BookRequestDTO;
import com.example.smartLibrary.entity.Book;
import com.example.smartLibrary.repository.BookRepository;
import com.example.smartLibrary.service.LibraryService;

@Service
public class LibraryServiceImpl implements LibraryService {
    private final BookRepository bookRepository;

    public LibraryServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book addBook(BookRequestDTO bookRequestDTO) {
        if(bookRepository.findByIsbn(bookRequestDTO.getIsbn()).isPresent()){
            throw new IllegalArgumentException("Book with ISBN already exists");
        } else {
            Book book = new Book();
            book.setTitle(bookRequestDTO.getTitle());
            book.setAuthor(bookRequestDTO.getAuthor());
            book.setIsbn(bookRequestDTO.getIsbn());
            book.setTotalCopies(bookRequestDTO.getTotalCopies());
            book.setAvailableCopies(bookRequestDTO.getTotalCopies());
            return bookRepository.save(book);
        }

    }
    
}
