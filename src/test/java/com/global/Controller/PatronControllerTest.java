package com.global.Controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.global.Model.Patron;
import com.global.Service.PatronService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PatronController.class)
public class PatronControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PatronService patronService;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void testGetAllPatrons() throws Exception {
		List<Patron> patrons = Arrays.asList(new Patron(1L, "John Doe", "johndoe@example.com"),
				new Patron(2L, "Jane Doe", "janedoe@example.com"));

		given(patronService.getAllPatrons()).willReturn(patrons);

		mockMvc.perform(get("/api/patrons")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0].name").value("John Doe")).andExpect(jsonPath("$[1].name").value("Jane Doe"));
	}

	@Test
	public void testGetPatronById() throws Exception {
		Patron patron = new Patron(1L, "John Doe", "johndoe@example.com");

		given(patronService.getPatronById(1L)).willReturn(patron);

		mockMvc.perform(get("/api/patrons/1")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.name").value("John Doe"));
	}

	@Test
	public void testAddPatron() throws Exception {
		Patron patron = new Patron(1L, "John Doe", "johndoe@example.com");

		given(patronService.addPatron(any(Patron.class))).willReturn(patron);

		mockMvc.perform(post("/api/patrons").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(patron))).andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value("John Doe"));
	}

	@Test
	public void testUpdatePatron() throws Exception {
		Patron patron = new Patron(1L, "John Doe", "johndoe@example.com");
		Patron updatedPatron = new Patron(1L, "John Updated", "johnupdated@example.com");

		given(patronService.updatePatron(anyLong(), any(Patron.class))).willReturn(updatedPatron);

		mockMvc.perform(put("/api/patrons/1").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(updatedPatron))).andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value("John Updated"));
	}

	@Test
	public void testDeletePatron() throws Exception {
		Mockito.doNothing().when(patronService).deletePatron(1L);

		mockMvc.perform(delete("/api/patrons/1")).andExpect(status().isOk());
	}
}
