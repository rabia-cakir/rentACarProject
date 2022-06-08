package com.kodlamaio.rentACar.business.abstracts;

import java.util.List;

import com.kodlamaio.rentACar.business.requests.brands.CreateBrandRequest;
import com.kodlamaio.rentACar.business.requests.cars.CreateCarRequest;
import com.kodlamaio.rentACar.business.requests.colors.CreateColorRequest;

public interface CarService {
	void add(CreateCarRequest createCarRequest);
	List<CreateCarRequest> getAll();
	void deleteById(int id);
	void update(CreateCarRequest createCarRequest, int id);

}
