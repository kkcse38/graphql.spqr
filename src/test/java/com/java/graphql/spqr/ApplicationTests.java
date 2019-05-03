package com.java.graphql.spqr;

import java.awt.PageAttributes.MediaType;
import java.util.Collections;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.java.graphql.spqr.service.BookService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private BookService bookService;
	
	Set<String> str;
	
	@Test
	public void contextLoads() throws Exception {
		
		Mockito.when(bookService.findAllBook(str)).thenReturn(Collections.emptyList());
		
		MvcResult result = mockMvc.perform(
				 MockMvcRequestBuilders.get("/")
				.accept(org.springframework.http.MediaType.APPLICATION_JSON))
				.andReturn();
		
		Mockito.verify(bookService).findAllBook(str);
	}

}
