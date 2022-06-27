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

import com.kodlamaio.rentACar.business.abstracts.AdditionalServiceItemService;
import com.kodlamaio.rentACar.business.requests.additionalServiceItemRequests.CreateAdditionalServiceItemRequest;
import com.kodlamaio.rentACar.business.requests.additionalServiceItemRequests.UpdateAdditionalServiceItemRequest;
import com.kodlamaio.rentACar.business.responses.additionalServiceItemResponses.AdditionalServiceItemResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/additionalserviceitems")
public class AdditionalServiceItemsController {
	
	private AdditionalServiceItemService service;
	
	public AdditionalServiceItemsController(AdditionalServiceItemService service) {
		super();
		this.service = service;
	}
	
	@GetMapping("/getall")
	public DataResult<List<AdditionalServiceItemResponse>> getAll()
	{
		return service.getAll();
	}

	@GetMapping("/{id}")
	public DataResult<AdditionalServiceItemResponse> getById(@PathVariable int id)
	{
		return service.findById(id);
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody CreateAdditionalServiceItemRequest createAdditionalServiceItemRequest) {
		return service.add(createAdditionalServiceItemRequest);
	}
	
	@PutMapping("/update")
	public Result add(@RequestBody UpdateAdditionalServiceItemRequest updateAdditionalServiceItemRequest) {
		return service.update(updateAdditionalServiceItemRequest);
	}
	

	@DeleteMapping("/{id}")
	public Result delete(@PathVariable int id) {
		return service.delete(id);
	}
}
