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

import com.kodlamaio.rentACar.business.abstracts.AdditionalServiceService;
import com.kodlamaio.rentACar.business.requests.additionalServiceRequests.CreateAdditionalServiceRequest;
import com.kodlamaio.rentACar.business.requests.additionalServiceRequests.UpdateAdditionalServiceRequest;
import com.kodlamaio.rentACar.business.responses.additionalServices.AdditionalServiceResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/additionalservices")
public class AdditionalServicesController {
	
	private AdditionalServiceService additionalServiceService;

	public AdditionalServicesController(AdditionalServiceService additionalServiceService) {
		super();
		this.additionalServiceService = additionalServiceService;
	}

	@GetMapping("/getallbyrentalid/{id}")
	public DataResult<List<AdditionalServiceResponse>> findAllByRentalId(@PathVariable int id) {
		return additionalServiceService.findAllByRentalId(id);

	}

	@PostMapping("/add")
	public Result add(@RequestBody CreateAdditionalServiceRequest createAdditionalService) {
		return this.additionalServiceService.add(createAdditionalService);
	}

	@PutMapping("/{id}")
	public Result update(@RequestBody UpdateAdditionalServiceRequest updateAdditionalServiceRequest) {
		return this.additionalServiceService.update(updateAdditionalServiceRequest);
	}

	@DeleteMapping("{id}")
	public Result update(@PathVariable int id) {
		return this.additionalServiceService.delete(id);
	}


}
