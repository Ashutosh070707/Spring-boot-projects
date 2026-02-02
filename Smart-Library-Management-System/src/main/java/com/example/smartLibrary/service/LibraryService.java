package com.example.smartLibrary.service;


import com.example.smartLibrary.dto.BookRequestDTO;
import com.example.smartLibrary.dto.BorrowRequestDTO;
import com.example.smartLibrary.dto.MemberRequestDTO;
import com.example.smartLibrary.entity.Book;
import com.example.smartLibrary.entity.BorrowRecord;
import com.example.smartLibrary.entity.Member;

public interface LibraryService {
    Book addBook(BookRequestDTO bookRequestDTO);
    Book getBookById(Long id);
    Member registerMember(MemberRequestDTO memberRequestDTO);
    Member getMemberById(Long id);
    BorrowRecord borrowBook(BorrowRequestDTO borrowRequestDTO);
    Book returnBook(BorrowRequestDTO borrowRequestDTO);
}
