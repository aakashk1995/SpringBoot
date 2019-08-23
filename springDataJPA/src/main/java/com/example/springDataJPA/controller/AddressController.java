package com.example.springDataJPA.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.springDataJPA.model.Address;
import com.example.springDataJPA.service.AddressService;



@RestController
@RequestMapping("/address")
public class AddressController {

	
	@Autowired
	private AddressService addressService;
	
	
	@GetMapping("/getAllAddress")
	public List<Address> address()
	{
		
		return addressService.getAddress();
	}
	
	

	@PostMapping("/saveAddress")
	public String saveAddress(@RequestBody Address address)
	{
		addressService.saveAddress(address);
		return "Address saved";
	}
	
	@PostMapping("/test")
	public @ResponseBody Map<String,String> test(@RequestParam Map<String, String> reqmap)
	{
		System.out.println(reqmap);
			return reqmap;
	}


}


