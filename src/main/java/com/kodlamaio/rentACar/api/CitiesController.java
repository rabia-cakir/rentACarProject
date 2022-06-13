package com.kodlamaio.rentACar.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentACar.business.abstracts.CityService;
import com.kodlamaio.rentACar.business.requests.cities.CreateCityRequest;

@RestController
@RequestMapping("/api/cities")
public class CitiesController {
	private CityService cityService;

	public CitiesController(CityService cityService) {
		super();
		this.cityService = cityService;
	}
	
	@PostMapping("/add")
	public void add(@RequestBody CreateCityRequest createCityRequest)
	{
		cityService.add(createCityRequest);
	}
	
	
	
	
}
