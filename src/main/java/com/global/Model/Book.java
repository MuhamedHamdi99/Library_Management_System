package com.global.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private String title;
	    private String author;
	    private int publicationYear;
	    private String isbn;
	    
	    
	    
	    
		public Book() {}
		public Book(Long id, String title, String author, int publicationYear, String isbn) {
			this.id = id;
			this.title = title;
			this.author = author;
			this.publicationYear = publicationYear;
			this.isbn = isbn;
		}
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getAuthor() {
			return author;
		}
		public void setAuthor(String author) {
			this.author = author;
		}
		public int getPublicationYear() {
			return publicationYear;
		}
		public void setPublicationYear(int publicationYear) {
			this.publicationYear = publicationYear;
		}
		public String getIsbn() {
			return isbn;
		}
		public void setIsbn(String isbn) {
			this.isbn = isbn;
		}
	    
	    
	    
}
