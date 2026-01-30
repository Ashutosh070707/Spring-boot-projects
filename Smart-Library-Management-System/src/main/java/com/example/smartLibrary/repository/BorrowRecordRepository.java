package com.example.smartLibrary.repository;

import java.lang.reflect.Member;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.smartLibrary.entity.BorrowRecord;

public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, Long> {
    long countByMemberAndIsActiveTrue(Member member);
}
