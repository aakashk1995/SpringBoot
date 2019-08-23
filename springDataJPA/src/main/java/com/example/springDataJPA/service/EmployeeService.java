package com.example.springDataJPA.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.example.springDataJPA.model.Address;
import com.example.springDataJPA.model.Employee;
import com.example.springDataJPA.repository.AddressRepository;
import com.example.springDataJPA.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private AddressRepository addressRepository;
	
	public void saveEmp(Employee employee) {
		
//		List<Address> addr = employee.getAddress();
//		
//		System.out.println("list of address " +addr);
		
		employeeRepository.save(employee);
		System.out.println("saved in database");

	}

	public List<Employee> retrieveEmployees() {
	
		return employeeRepository.findAll();

	}
	

	public boolean deleteEmployee(Long employeeId) {
 
	boolean flag = false;
			
		Optional<Employee> emp =  employeeRepository.findById(employeeId);
		if(emp.isPresent())
		{
			int a = addressRepository.deleteAllByEmployeeId(employeeId);
			System.out.println("value of a" + a);
			if(a>=1)
			{
				employeeRepository.deleteEmp(employeeId);
			}
		}else {
			flag=true;
			return flag;
		}
		return flag;
		
}

	public boolean updateSalary(Employee employee) {
		
		Optional<Employee> existingEmp = employeeRepository.findById(employee.getId());
		if (existingEmp.isPresent()) {
			employeeRepository.updateSalary(employee.getSalary(), employee.getId());
			return true;
		}

		return false;

	}

	public Optional<Employee> findByEmployeeId(Long empid) throws Exception{

	
		Optional<Employee> emp = employeeRepository.findById(empid);
		System.out.println("Employee of findByEmployeeID  " + emp);
		//System.out.println("Employee of findByEmployeeID Object " + emp.get());
		System.out.println("Employee " + emp.get().getId());
		 return emp;
	
	}

	public void saveEmployeeAddress(Employee employee) {
		
		
		List<Address> addrls =  employee.getAddress();
		System.out.println(addrls);
		
        employeeRepository.save(employee);
		
        System.out.println("Employee with address saved succesfully");
		
		
	}
	

	public void updateEmployeeAddress(Employee employee) {

		Optional<Employee> emp =  employeeRepository.findById(employee.getId());
	 
		
		if(emp.isPresent())
		{
			List<Address> lsaddr=addressRepository.findAllByEmployeeId(employee.getId());
			System.out.println(lsaddr);
			
			for(int i =0;i<employee.getAddress().size();i++)
			{
				lsaddr.get(i).setCity(employee.getAddress().get(i).getCity());
				lsaddr.get(i).setPincode(employee.getAddress().get(i).getPincode());
				lsaddr.get(i).setState(employee.getAddress().get(i).getState());
			}
			
			addressRepository.saveAll(lsaddr);
		}
		
	}
	
	public boolean updateEmp(Employee employee)
	{
		boolean flag = false;
		
		Optional<Employee> emp = employeeRepository.findById(employee.getId());
		if(emp.isPresent()) 
		{
			System.out.println("Update Emplopyee" +  emp.get());
			employeeRepository.save(employee);
		}else {
			flag = true;
			return flag;
		}
		return flag;
	}

	public List<Employee> findAllEmpByPage(Pageable pageable) {
		// TODO Auto-generated method stub
		Page<Employee> pgemp = employeeRepository.findAll(pageable);
		return pgemp.getContent();
	}

	public List<Employee> sortByAge(Integer age) {
		
		return employeeRepository.findByAgeGreaterThan(age, new Sort(Direction.DESC, "age"));
		
	}
   
}







