package com.kodlamaio.rentACar.business.abstracts;

import com.kodlamaio.rentACar.business.requests.cities.CreateCityRequest;
import com.kodlamaio.rentACar.entities.concretes.City;

public interface CityService {
	void add(CreateCityRequest createCityRequest);
	City findById(int id);
}
