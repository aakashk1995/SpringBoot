package com.example.springDataJPA.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "ADDRESS")
public class Address {

	@Id
	@GeneratedValue
	@Column(name = "ADDR_ID")
	
	private Long addr_id;

	@NotNull(message="city should not be null")
	@Column(name = "CITY")
	private String city;

	@NotNull(message="state should not be null")
	@Column(name = "STATE")
	private String state;

	@NotNull(message="pincode should not be null")
	@Column(name = "PINCODE")
	private Integer pincode;

	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "FK_EMP_ID", referencedColumnName="ID")
	@JsonBackReference
	@Valid
	@NotNull
	private Employee emp;

	public Long getAddr_id() {
		return addr_id;
	}

	public void setAddr_id(Long addr_id) {
		this.addr_id = addr_id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getPincode() {
		return pincode;
	}

	public void setPincode(Integer pincode) {
		this.pincode = pincode;
	}

	public Employee getEmp() {
		return emp;
	}

	
	public void setEmp(Employee emp) {
		this.emp = emp;
	}
	
	
	
	


}
