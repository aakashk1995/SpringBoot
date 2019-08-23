package com.example.springDataJPA.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.springDataJPA.exception.EmployeeNotFoundException;
import com.example.springDataJPA.exception.ExceptionResponse;
import com.example.springDataJPA.exception.SuccessMessage;
import com.example.springDataJPA.model.Address;
import com.example.springDataJPA.model.Employee;
import com.example.springDataJPA.service.AddressService;
import com.example.springDataJPA.service.EmployeeService;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/employee")
public class EmployeeRestController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private AddressService addressService;

	
	
	
	
	
	@GetMapping("/listpage")
	public List<Employee> employeesPageable(Pageable pageable) {
	
		return employeeService.findAllEmpByPage(pageable);
		

	}
	
	@GetMapping("/getAllEmp")
	public List<Employee> getEmployees() {
		return employeeService.retrieveEmployees();

	}

	@PostMapping("/saveEmp")
	public ResponseEntity<Object> saveEmployee(@Valid @RequestBody Employee employee) {

		List<Address> addr = employee.getAddress();
		
		System.out.println("list of address " +addr);
		
		System.out.println("Empty" + addr.isEmpty());

		System.out.println("size of addr " + addr.size());
		
		
		
		employeeService.saveEmp(employee);
		SuccessMessage success = new SuccessMessage();
		success.setMessage("Employee Saved Successfully");
		return new ResponseEntity<>("Employee Saved Successfully", HttpStatus.OK);

	}
	

	@PutMapping("/updateSal")
	public String updateEmpSalary(@RequestBody Employee employee) {
		
		System.out.println("update employee id  " + employee.getId() );
		employeeService.updateSalary(employee);

		return "Employee updated";

	}

	@DeleteMapping(value = "/deleteEmp/{employeeId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Object> deleteEmployee(@PathVariable Long employeeId) {

		if (employeeService.deleteEmployee(employeeId)) {
			throw new EmployeeNotFoundException();
		}

		SuccessMessage success = new SuccessMessage();
		success.setMessage("Employee Deleted Successfully");

		ResponseEntity<Object> responseEntity = new ResponseEntity<>(success, HttpStatus.OK);

		return responseEntity;
	}

	@GetMapping("/getEmpById/{empid}")
	public ResponseEntity<Object> getEmployeeById(@PathVariable Long empid) {

		System.out.println("Employee id" + empid);

		try {
			Optional<Employee> emp = employeeService.findByEmployeeId(empid);	
			if (emp.isPresent()) {

				return new ResponseEntity<>(emp.get(), HttpStatus.OK);
			}
		}catch(Exception ex)
		{
			ex.printStackTrace();
			
		}
		ExceptionResponse exresp = new ExceptionResponse();
		exresp.setErrorMessage("Employee Not found");
		exresp.setStatus(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(exresp,HttpStatus.NOT_FOUND);
		

	}

	@PostMapping(value = "/saveEmployeeAddress")
	public String saveEmployeeAddress(@RequestBody Employee employee) {
		System.out.println("id  " + employee.getId() + " address  " + employee.getAddress());
		employeeService.saveEmployeeAddress(employee);

		return "Employee details with address saved successfully";
	}

	@PutMapping("/updateEmployeeAddress/")
	public String updateEmployeeAddress(@RequestBody Employee employee) {
		employeeService.updateEmployeeAddress(employee);

		return "Employee with address updated ";
	}

	@PutMapping("/updateEmp")
	public ResponseEntity<Object> updateEmp(@RequestBody Employee employee) {
		if (employeeService.updateEmp(employee)) {
			throw new EmployeeNotFoundException();
		}
		SuccessMessage success = new SuccessMessage();
		success.setMessage("Employee Updated Successfully");
		return new ResponseEntity<>(success, HttpStatus.OK);
	}
	
	
	/*  
	 * Sorting controller 
	 * 
	 * */
	
	@GetMapping("/getsorteddata")
	public List<Employee> sortMethod(@RequestParam Integer age)
	{
		System.out.println("age is " + age);
	  return employeeService.sortByAge(age);
	}

}
