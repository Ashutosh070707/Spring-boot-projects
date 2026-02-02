package com.example.smartLibrary.service.impl;

import java.time.LocalDate;
import org.springframework.stereotype.Service;

import com.example.smartLibrary.dto.BookRequestDTO;
import com.example.smartLibrary.dto.BorrowRequestDTO;
import com.example.smartLibrary.dto.MemberRequestDTO;
import com.example.smartLibrary.entity.Book;
import com.example.smartLibrary.entity.BorrowRecord;
import com.example.smartLibrary.entity.Member;
import com.example.smartLibrary.repository.BookRepository;
import com.example.smartLibrary.repository.BorrowRecordRepository;
import com.example.smartLibrary.repository.MemberRepository;
import com.example.smartLibrary.service.LibraryService;

import jakarta.transaction.Transactional;

@Service
public class LibraryServiceImpl implements LibraryService {
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;
    private final BorrowRecordRepository borrowRecordRepository;

    public LibraryServiceImpl(BookRepository bookRepository, MemberRepository memberRepository,
            BorrowRecordRepository borrowRecordRepository) {
        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
        this.borrowRecordRepository = borrowRecordRepository;
    }

    @Override
    public Book addBook(BookRequestDTO bookRequestDTO) {
        if (bookRepository.findByIsbn(bookRequestDTO.getIsbn()).isPresent()) {
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
    @Override
    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Book not found with id: " + id));
    }



    @Override
    public Member registerMember(MemberRequestDTO memberRequestDTO) {
        if (memberRepository.findByEmail(memberRequestDTO.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Member with email already exists");
        } else {
            Member member = new Member();
            member.setName(memberRequestDTO.getName());
            member.setEmail(memberRequestDTO.getEmail());
            member.setMembershipDate(LocalDate.now());
            member.setTotalBooksBorrowed(0L);
            return memberRepository.save(member);
        }
    }

    @Override
    public Member getMemberById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Member not found with id: " + id));
    }

    @Transactional
    @Override
    public BorrowRecord borrowBook(BorrowRequestDTO borrowRequestDTO) {
        // if(memberRepository.findById(borrowRequestDTO.getMemberId()).isEmpty() ||
        // bookRepository.findById(borrowRequestDTO.getBookId()).isEmpty()){
        // throw new IllegalArgumentException("Invalid member Id or book Id");
        // }
        // Book book = bookRepository.findById(borrowRequestDTO.getBookId()).get();
        // Member member =
        // memberRepository.findById(borrowRequestDTO.getMemberId()).get();

        // 1. Fetch & Validate in one step (Queries DB once per entity)
        Member member = memberRepository.findById(borrowRequestDTO.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid member Id"));

        Book book = bookRepository.findById(borrowRequestDTO.getBookId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid book Id")); 
        
        if(borrowRecordRepository.findByMemberIdAndBookIdAndIsActiveTrue(borrowRequestDTO.getMemberId(), borrowRequestDTO.getBookId()).isPresent()){
            throw new IllegalArgumentException("Member has already borrowed this book and not returned yet");
        }

        if (book.getAvailableCopies() <= 0) {
            throw new IllegalArgumentException("No copies available for the book: " + book.getTitle());
        }

        Long totalCount = borrowRecordRepository.countByMemberIdAndIsActiveTrue(borrowRequestDTO.getMemberId());
        if (totalCount >= 3) {
            throw new IllegalArgumentException("Member has reached the maximum borrow limit");
        }

        decrementBookCopies(book);
        incrementTotalBooksBorrowed(member);
        BorrowRecord borrowRecord = new BorrowRecord();
        borrowRecord.setBook(book);
        borrowRecord.setMember(member);
        borrowRecord.setBorrowDate(LocalDate.now());
        borrowRecord.setActive(true);
        return borrowRecordRepository.save(borrowRecord);
    }

    private void decrementBookCopies(Book book) {
        if (book.getAvailableCopies() <= 0) {
            throw new IllegalArgumentException("No copies available to decrement");
        }
        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookRepository.save(book);
    }
    private void incrementTotalBooksBorrowed(Member member) {
        member.setTotalBooksBorrowed(member.getTotalBooksBorrowed() + 1);
        memberRepository.save(member);
    }

    @Override
    @Transactional
    public Book returnBook(BorrowRequestDTO borrowRequestDTO) {
        Member member = memberRepository.findById(borrowRequestDTO.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid member Id"));

        Book book = bookRepository.findById(borrowRequestDTO.getBookId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid book Id")); 

        BorrowRecord borrowRecord = borrowRecordRepository.findByMemberIdAndBookIdAndIsActiveTrue(borrowRequestDTO.getMemberId(), borrowRequestDTO.getBookId()).orElseThrow(() -> new IllegalArgumentException("No active borrow record found for this member and book"));

        borrowRecord.setReturnDate(LocalDate.now());
        borrowRecord.setActive(false);
        borrowRecordRepository.save(borrowRecord);

        member.setTotalBooksBorrowed(member.getTotalBooksBorrowed()-1);
        memberRepository.save(member);
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        return bookRepository.save(book);
    }
}