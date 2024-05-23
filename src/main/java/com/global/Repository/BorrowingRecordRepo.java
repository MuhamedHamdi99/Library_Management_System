package com.global.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.global.Model.BorrowingRecord;

@Repository
public interface BorrowingRecordRepo extends JpaRepository<BorrowingRecord, Long>{

	BorrowingRecord findByBookIdAndPatronId(Long bookId, Long patronId);

}
