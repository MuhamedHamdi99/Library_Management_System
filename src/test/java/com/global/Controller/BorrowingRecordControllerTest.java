package com.global.Controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.global.Model.Book;
import com.global.Model.BorrowingRecord;
import com.global.Model.Patron;
import com.global.Service.BorrowingRecordService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BorrowingRecordController.class)
public class BorrowingRecordControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BorrowingRecordService borrowingRecordService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testBorrowBook() throws Exception {
        Book book = new Book(1L, "Book Title", "Author", 2021, "1234567890");
        Patron patron = new Patron(1L, "John Doe", "johndoe@example.com");
        BorrowingRecord borrowingRecord = new BorrowingRecord(1L, book, patron, LocalDate.now(), null);

        given(borrowingRecordService.borrowBook(anyLong(), anyLong())).willReturn(borrowingRecord);

        mockMvc.perform(post("/api/borrow/1/patron/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(borrowingRecord)));
    }

    @Test
    public void testReturnBook() throws Exception {
        Book book = new Book(1L, "Book Title", "Author", 2021, "1234567890");
        Patron patron = new Patron(1L, "John Doe", "johndoe@example.com");
        BorrowingRecord borrowingRecord = new BorrowingRecord(1L, book, patron, LocalDate.now().minusDays(5), LocalDate.now());

        given(borrowingRecordService.returnBook(anyLong(), anyLong())).willReturn(borrowingRecord);

        mockMvc.perform(put("/api/return/1/patron/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(borrowingRecord)));
    }
}