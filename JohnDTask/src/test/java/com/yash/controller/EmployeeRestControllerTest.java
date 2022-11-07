package com.yash.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yash.model.Employee;
import com.yash.repository.EmployeeRepository;
import com.yash.service.EmployeeService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration
@WebMvcTest
public class EmployeeRestControllerTest {
	@Autowired
	MockMvc mockMvc; 

	/*
	 * @Autowired private WebApplicationContext webApplicationContext;
	 */

	@MockBean
	EmployeeRepository employeeRepository;

	@MockBean
	EmployeeService employeeService;

	/*
	 * @Before public void setUp() { mockMvc =
	 * MockMvcBuilders.webAppContextSetup(webApplicationContext).build(); }
	 */

	@Test
	@WithMockUser(roles = "Admin")
	public void createNewEmployeeTest() throws Exception {
		Employee emp = new Employee();
		emp.setId(1);
		emp.setName("Nilesh");
		emp.setDept("IT");
		emp.setAddress("Pune");
		
		ObjectMapper mapper = new ObjectMapper();
		this.mockMvc
				.perform(post("/postEmployee").contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(emp)))

				.andExpect(status().isOk());
	}

	@Test
	@WithMockUser(roles = { "Admin", "User" })
	public void getAllEmployeeTest() throws Exception {

		mockMvc.perform(get("/getEmployee"))

				.andExpect(status().isOk());

		// .andExpect(content().json("[{}, {}]"));

	}
}