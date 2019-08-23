package com.example.springDataJPA.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name = "EMPLOYEE")
public class Employee {
	

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Long id;
	
	@NotNull(message="name should not be null")
	@Size(min=1,  max=15 ,message="Name should be min 1 and max 15")
	@Column(name = "EMPLOYEE_NAME")
	private String name;
	
	@NotNull(message="salary should not be null")
	@Column(name = "EMPLOYEE_SALARY")
	private Integer salary;
	
	@NotNull(message="department should not be null")
	@Column(name = "DEPARTMENT")
	private String department;

	@Column(name="AGE")
	 @Min(value=18, message="must be equal or greater than 18")  
    @Max(value=60, message="must be equal or less than 45")  
	@NotNull(message="age should not be null")
	private Integer age;
	
	@Column(name="EMAIL")
	@com.example.springDataJPA.customValidator.email
	@NotNull(message="email id is required")
	private String email;
	
	
	@OneToMany(mappedBy="emp", cascade={CascadeType.ALL}, orphanRemoval=true,fetch=FetchType.LAZY)
	@JsonManagedReference
	@NotEmpty(message="Atleast 1 addresss should present")
	@Valid
	private List<Address> address = new ArrayList<Address>();

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSalary() {
		return salary;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public List<Address> getAddress() {
		return address;
	}

	public void setAddress(List<Address> address) {
		this.address = address;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	
	

}
