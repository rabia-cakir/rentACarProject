package com.kodlamaio.rentACar.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentACar.business.abstracts.RentalService;
import com.kodlamaio.rentACar.business.requests.rentals.CreateRentalRequest;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;

@RestController
@RequestMapping("/api/rental")
public class RentalsController {
	
	private RentalService rentalService;

	public RentalsController(RentalService rentalService) {
		super();
		this.rentalService = rentalService;
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody CreateRentalRequest createRentalRequest)
	{
		rentalService.add(createRentalRequest);
		return new SuccessResult();
	}

}
