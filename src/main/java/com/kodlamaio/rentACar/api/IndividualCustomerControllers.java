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

import com.kodlamaio.rentACar.business.abstracts.IndividualCustomerService;
import com.kodlamaio.rentACar.business.requests.userRequests.individualCustomerRequests.CreateIndividualCustomerRequest;
import com.kodlamaio.rentACar.business.requests.userRequests.individualCustomerRequests.UpdateIndividualCustomerRequest;
import com.kodlamaio.rentACar.business.responses.userResponses.IndividualCustomerResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/individualCustomers")
public class IndividualCustomerControllers {

	private IndividualCustomerService individualCustomerService;

	public IndividualCustomerControllers(IndividualCustomerService individualCustomerService) {
		super();
		this.individualCustomerService = individualCustomerService;
	}

	@GetMapping("/getall")
	public DataResult<List<IndividualCustomerResponse>> getAll() {
		return individualCustomerService.getAll();
	}

	@PostMapping("/add")
	public Result add(@RequestBody CreateIndividualCustomerRequest createIndividualCustomerRequest) {
		return individualCustomerService.add(createIndividualCustomerRequest);
	}

	@PutMapping("/update")
	public Result update(@RequestBody UpdateIndividualCustomerRequest updateIndividualCustomerRequest) {
		return individualCustomerService.update(updateIndividualCustomerRequest);
	}

	@DeleteMapping("/{id}")
	public Result delete(@PathVariable int id) {
		return individualCustomerService.delete(id);
	}
}