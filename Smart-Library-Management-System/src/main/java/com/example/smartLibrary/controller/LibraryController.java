package com.example.smartLibrary.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.smartLibrary.dto.BookRequestDTO;
import com.example.smartLibrary.dto.BorrowRequestDTO;
import com.example.smartLibrary.dto.MemberRequestDTO;
import com.example.smartLibrary.entity.Book;
import com.example.smartLibrary.entity.BorrowRecord;
import com.example.smartLibrary.entity.Member;
import com.example.smartLibrary.service.LibraryService;

@RestController
@RequestMapping("/api/library")
public class LibraryController {
    LibraryService libraryService;

    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }
    
    @PostMapping("/createBook")
    public ResponseEntity<?> createBook(@RequestBody BookRequestDTO request) {
        try{
            Book savedBook = libraryService.addBook(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @GetMapping("/getBook/{id}")
    public ResponseEntity<?> getBookById(@PathVariable Long id){
        try{
            Book book = libraryService.getBookById(id);
            return ResponseEntity.status(HttpStatus.OK).body(book);
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    @PostMapping("/registerMember")
    public ResponseEntity<?> registerMember(@RequestBody MemberRequestDTO request){
        try{
            Member savedMember = libraryService.registerMember(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMember);
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/getMember/{id}")
    public ResponseEntity<?> getMemberById(@PathVariable Long id){
        try{
            Member member = libraryService.getMemberById(id);
            return ResponseEntity.status(HttpStatus.OK).body(member);
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/borrowBook")
    public ResponseEntity<?> borrowBook(@RequestBody BorrowRequestDTO request){
        try {
            BorrowRecord borrowRecord = libraryService.borrowBook(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(borrowRecord);
        } catch(IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }
    }

    @PostMapping("/returnBook")
    public ResponseEntity<?> returnBook(@RequestBody BorrowRequestDTO request){
        try {
            Book returnedBook = libraryService.returnBook(request);
            return ResponseEntity.status(HttpStatus.OK).body(returnedBook);
        } catch(IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }
    }
} 
