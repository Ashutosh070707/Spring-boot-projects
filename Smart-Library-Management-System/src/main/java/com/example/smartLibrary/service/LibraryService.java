package com.example.smartLibrary.service;

import com.example.smartLibrary.dto.BookRequestDTO;
import com.example.smartLibrary.entity.Book;

public interface LibraryService {
    Book addBook(BookRequestDTO bookRequestDTO);
}
