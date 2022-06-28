package com.kodlamaio.rentACar.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentACar.business.abstracts.AddressService;
import com.kodlamaio.rentACar.business.requests.addressRequests.CreateAddressRequest;
import com.kodlamaio.rentACar.business.requests.addressRequests.UpdateAddressRequest;
import com.kodlamaio.rentACar.business.responses.addressResponses.AddressResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/address")
public class AddressControllers {
	
private AddressService addressService;
	
	public AddressControllers(AddressService addressService) {
		this.addressService = addressService;
	}
	
	
	@GetMapping("getAll")
	public DataResult<List<AddressResponse>> getAll(){
		return this.addressService.getAll();
	}
	

	@PostMapping("/add")
	public Result add(@RequestBody CreateAddressRequest createAddressRequest) {
		return this.addressService.add(createAddressRequest);
	}
	
	@PostMapping("/update")
	public Result update(@RequestBody UpdateAddressRequest updateAddressRequest) {
		return this.addressService.update(updateAddressRequest);
	}
	
	@PostMapping("/{id}")
	public Result delete(@PathVariable int id) {
		return this.addressService.delete(id);
	}
	

	@GetMapping("getAllBillAddress")
	public DataResult<List<AddressResponse>> getAllBillAddress(@RequestParam int userId, int addressType){
		return this.addressService.getAllBillAddress(userId, addressType);
	}
	
	@GetMapping("getAllContactAddress")
	public DataResult<List<AddressResponse>> getAllContactAddress(@RequestParam int userId, int addressType){
		return this.addressService.getAllContactAddress(userId, addressType);
	}

}
