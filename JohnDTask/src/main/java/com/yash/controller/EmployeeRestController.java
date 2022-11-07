package com.yash.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yash.model.Employee;
import com.yash.service.EmployeeService;

@RestController
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class EmployeeRestController {

		@Autowired
		EmployeeService employeeService;
		
		@PreAuthorize("hasRole('Admin')")
		@PostMapping(value = "/postEmployee")
		public Employee createNewEmployee(@RequestBody Employee employee) {
			return employeeService.saveEmployeeObject(employee);
		}
		
		@PreAuthorize("hasRole('Admin')or hasRole('User')")
		@GetMapping(value = "/getEmployee")
		public List<Employee> getAllEmployee(){
			return employeeService.getAllEmployee();
		}
}
