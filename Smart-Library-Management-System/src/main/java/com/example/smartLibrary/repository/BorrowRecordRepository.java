package com.example.smartLibrary.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.smartLibrary.entity.BorrowRecord;

public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, Long> {
    Long countByMemberIdAndIsActiveTrue(Long memberId);
    Optional<BorrowRecord> findByMemberIdAndBookIdAndIsActiveTrue(Long memberId,  Long bookId);
    Optional<BorrowRecord> findByMemberIdAndBookIdAndIsActiveFalse(Long memberId, Long bookId);
}
