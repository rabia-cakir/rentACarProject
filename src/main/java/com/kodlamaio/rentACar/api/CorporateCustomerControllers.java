package com.kodlamaio.rentACar.api;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentACar.business.abstracts.CorporateCustomerService;
import com.kodlamaio.rentACar.business.requests.userRequests.corporateCustomerRequests.CreateCorporateCustomerRequest;
import com.kodlamaio.rentACar.business.requests.userRequests.corporateCustomerRequests.UpdateCorporateCustomerRequest;
import com.kodlamaio.rentACar.business.responses.userResponses.CorporateCustomerResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/corporateCustomers")
public class CorporateCustomerControllers {
	
	private CorporateCustomerService corporateCustomerService;

	public CorporateCustomerControllers(CorporateCustomerService corporateCustomerService) {
		super();
		this.corporateCustomerService = corporateCustomerService;
	}
	
	@GetMapping("/getall")
	public DataResult<List<CorporateCustomerResponse>> getAll()
	{
		return corporateCustomerService.getAll();
	}

	@PostMapping("/add")
	public Result add(@RequestBody CreateCorporateCustomerRequest createCorporateCustomerRequest)
	{
		return corporateCustomerService.add(createCorporateCustomerRequest);
	}
	
	@PutMapping("/update")
	public Result update(@RequestBody UpdateCorporateCustomerRequest updateCorporateCustomerRequest)
	{
		return corporateCustomerService.update(updateCorporateCustomerRequest);
	}
	
	@DeleteMapping("/{id}")
	public Result delete(@PathVariable int id)
	{
		return corporateCustomerService.delete(id);
	}
}
