package com.yash.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.yash.model.Employee;
import com.yash.repository.EmployeeRepository;

@TestMethodOrder(org.junit.jupiter.api.MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = { EmployeeServiceTest.class })
public class EmployeeServiceTest {

	@Mock
	EmployeeRepository employeeRepository;

	@InjectMocks
	EmployeeService employeeService;

	@Test
	@Order(1)
	public void test_addEmployee() {
		Employee employee = new Employee();
		employee.setId(1);
		employee.setName("someshwar");
		employee.setAddress("pune");
		employee.setDept("dev");

		when(employeeRepository.save(employee)).thenReturn(employee);
		assertThat(employeeService.saveEmployeeObject(employee)).isEqualTo(employee);
	}

	@Test
	@Order(2)
	public void test_getAllEmployee() {
		List<Employee> empList = new ArrayList<>();

		Employee employee1 = new Employee();
		employee1.setId(1);
		employee1.setName("mahesh");
		employee1.setAddress("mumbai");
		employee1.setDept("dev-oops");

		Employee employee2 = new Employee();
		employee1.setId(2);
		employee1.setName("anurag");
		employee1.setAddress("nagpur");
		employee1.setDept("testing");

		empList.add(employee1);
		empList.add(employee2);

		when(employeeRepository.findAll()).thenReturn(empList);
		assertThat(employeeService.getAllEmployee()).isEqualTo(empList);
	}

}
