package com.global.Controller;

import com.global.Model.Book;
import com.global.Service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BookController.class)
public class BookControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private BookService bookService;

	@Test
	public void testGetAllBooks() throws Exception {
		List<Book> books = Arrays.asList(new Book(1L, "Title1", "Author1", 2023, "1234567890"),
				new Book(2L, "Title2", "Author2", 2022, "0987654321"));

		given(bookService.getAllBooks()).willReturn(books);

		mockMvc.perform(get("/api/books")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0].title").value("Title1")).andExpect(jsonPath("$[1].title").value("Title2"));
	}

	@Test
	public void testGetBookById() throws Exception {
		Book book = new Book(1L, "Title1", "Author1", 2023, "1234567890");

		given(bookService.getBookById(1L)).willReturn(book);

		mockMvc.perform(get("/api/books/1")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.title").value("Title1"));
	}

	@Test
	public void testAddBook() throws Exception {
		Book book = new Book(1L, "Title1", "Author1", 2023, "1234567890");

		given(bookService.addBook(any(Book.class))).willReturn(book);

		mockMvc.perform(post("/api/books").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(book))).andExpect(status().isOk())
				.andExpect(jsonPath("$.title").value("Title1"));
	}

	@Test
	public void testUpdateBook() throws Exception {
		Book book = new Book(1L, "Updated Title", "Author1", 2023, "1234567890");

		given(bookService.updateBook(anyLong(), any(Book.class))).willReturn(book);

		mockMvc.perform(put("/api/books/1").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(book))).andExpect(status().isOk())
				.andExpect(jsonPath("$.title").value("Updated Title"));
	}

	@Test
	public void testDeleteBook() throws Exception {
		Mockito.doNothing().when(bookService).deleteBook(1L);

		mockMvc.perform(delete("/api/books/1")).andExpect(status().isOk());
	}
}