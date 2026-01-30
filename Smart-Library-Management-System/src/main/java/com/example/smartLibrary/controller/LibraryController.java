package com.example.smartLibrary.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.smartLibrary.dto.BookRequestDTO;
import com.example.smartLibrary.entity.Book;
import com.example.smartLibrary.service.LibraryService;

@RestController
@RequestMapping("/api/library")
public class LibraryController {
    LibraryService libraryService;

    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }
    
    @PostMapping("/createBook")
    public ResponseEntity<Book> createBook(@RequestBody BookRequestDTO request) {
        Book savedBook = libraryService.addBook(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
    }
}
