package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
class DemoApplicationTests {

	protected MockMvc mockMvc;

	@BeforeEach
	public void setUp(WebApplicationContext context) {
		this.mockMvc = MockMvcBuilders
				.webAppContextSetup(context)
				.build();
	}

	@Test
	void contextLoads() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/mortgage")
				.param("cost", "100000000")
				.param("downPayment", "10000000")
				.param("years", "1")
		)
				.andExpect(MockMvcResultMatchers.jsonPath("$.monthlyCharge", is(7950000)));
	}

}
