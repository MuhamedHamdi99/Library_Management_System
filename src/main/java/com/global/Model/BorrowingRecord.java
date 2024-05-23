package com.global.Model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BorrowingRecord {

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    
	    @ManyToOne
	    @JoinColumn(name = "book_id", nullable = false)
	    private Book book;
	    
	    @ManyToOne
	    @JoinColumn(name = "patron_id", nullable = false)
	    private Patron patron;
	    
	    private LocalDate borrowDate;
	    private LocalDate returnDate;
	    
		public BorrowingRecord() {		}
		public BorrowingRecord(Long id, Book book, Patron patron, LocalDate borrowDate, LocalDate returnDate) {
			this.id = id;
			this.book = book;
			this.patron = patron;
			this.borrowDate = borrowDate;
			this.returnDate = returnDate;
		}



		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public Book getBook() {
			return book;
		}
		public void setBook(Book book) {
			this.book = book;
		}
		public Patron getPatron() {
			return patron;
		}
		public void setPatron(Patron patron) {
			this.patron = patron;
		}
		public LocalDate getBorrowDate() {
			return borrowDate;
		}
		public void setBorrowDate(LocalDate borrowDate) {
			this.borrowDate = borrowDate;
		}
		public LocalDate getReturnDate() {
			return returnDate;
		}
		public void setReturnDate(LocalDate returnDate) {
			this.returnDate = returnDate;
		}

	
}
