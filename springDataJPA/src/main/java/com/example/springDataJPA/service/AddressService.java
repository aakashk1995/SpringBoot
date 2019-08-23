package com.example.springDataJPA.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springDataJPA.model.Address;
import com.example.springDataJPA.repository.AddressRepository;


@Service
public class AddressService {

	@Autowired
	private AddressRepository addressRepository;
	
	
	public List<Address> getAddress() {
	
		return addressRepository.findAll();
	}


	public void saveAddress(Address address) {
		
		addressRepository.save(address);
		
	}
	

	
	
	
	

}
