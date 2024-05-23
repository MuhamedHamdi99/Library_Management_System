package com.global.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.global.Model.Book;
import com.global.Repository.BookRepo;

@Service
public class BookService {

	@Autowired
	BookRepo bookRepo;
	
	 public List<Book> getAllBooks() {
	        return bookRepo.findAll();
	    }

	    public Book getBookById(Long id) {
	        return bookRepo.findById(id).get();
	        //.orElseThrow(() -> new ResourceNotFoundException("Book not found"));
	    }

	    public Book addBook(Book book) {
	        return bookRepo.save(book);
	    }

	    public Book updateBook(Long id, Book bookDetails) {
	        Book book = getBookById(id);
	        book.setTitle(bookDetails.getTitle());
	        book.setAuthor(bookDetails.getAuthor());
	        book.setPublicationYear(bookDetails.getPublicationYear());
	        book.setIsbn(bookDetails.getIsbn());
	        return bookRepo.save(book);
	    }

	    public void deleteBook(Long id) {
	        Book book = getBookById(id);
	        bookRepo.delete(book);
	    }
	}
