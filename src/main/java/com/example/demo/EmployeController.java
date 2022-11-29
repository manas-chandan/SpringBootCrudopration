package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/emp")
public class EmployeController {
	@Autowired
	private EmployeeRepository er;
	@GetMapping
	public List<Employee> getAllEmployee()
	{
		return er.findAll();
	}
	
	@PutMapping("/add")
	public String addEmployee(@RequestBody Employee e) {
		Optional<Employee> findById = er.findById(e.getId());
		
		if(findById.isPresent())
		{
			System.out.println("already present");
			return "employee alreday present";
		}
		else {
			System.out.println("new entry");
			er.save(e);
			return "Employee saved successfully";
		}
			
	}

	@GetMapping("/{id}")
	public Optional<Employee> particularEmployee(@PathVariable int id ) {
		Optional<Employee> findById = er.findById(id);
		return findById;
	}
	
	@PostMapping("/{id}")
	public String deleteEmployee(@PathVariable int id) {
		Optional<Employee> findById = er.findById(id);
		if(findById.isPresent())
		{
			er.deleteById(id);
			return "Employee Deleted successfully.";
		}
		else
			return "Employee Not Found on this id.";
	}
	
	@PutMapping("/{id}")
	public String updateSpecificEmployee(@RequestBody Employee e,@PathVariable int id) {
		Employee uemp = er.findById(id).get();
		if(uemp!=null)
		{
			uemp.setId(e.getId());
			uemp.setName(e.getName());
			er.save(uemp);
			return "Employee data updated";
		}
		else {
			return "No data found on this id.";
		}
	}
	
}
