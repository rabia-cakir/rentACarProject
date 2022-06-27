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

import com.kodlamaio.rentACar.business.abstracts.CityService;
import com.kodlamaio.rentACar.business.requests.cityRequests.CreateCityRequest;
import com.kodlamaio.rentACar.business.requests.cityRequests.UpdateCityRequest;
import com.kodlamaio.rentACar.business.responses.cityResponses.CityResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/cities")
public class CitiesController {
	private CityService cityService;

	public CitiesController(CityService cityService) {
		super();
		this.cityService = cityService;
	}
	
	@GetMapping("/getall")
	public DataResult<List<CityResponse>> getAll()
	{
		return cityService.getAll();
	}
	
	
	@PostMapping("/add")
	public Result add(@RequestBody CreateCityRequest createCityRequest)
	{
		return cityService.add(createCityRequest);
	}
	
	@PutMapping("/update")
	public Result update(@RequestBody UpdateCityRequest updateCityRequest)
	{
		return cityService.update(updateCityRequest);
	}
	
	@DeleteMapping("/{id}")
	public Result delete(@PathVariable int id)
	{
		return cityService.delete(id);
	}
	
	
}
