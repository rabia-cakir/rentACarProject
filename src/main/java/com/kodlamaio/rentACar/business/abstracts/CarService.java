package com.kodlamaio.rentACar.business.abstracts;

import java.util.List;

import com.kodlamaio.rentACar.business.requests.carRequests.CreateCarRequest;
import com.kodlamaio.rentACar.business.requests.carRequests.UpdateCarRequest;
import com.kodlamaio.rentACar.business.responses.carResponses.CarResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.entities.concretes.Car;

public interface CarService {
	Result add(CreateCarRequest createCarRequest);

	DataResult<List<CarResponse>> getAll();

	Result deleteById(int id);

	Result update(UpdateCarRequest updateCarRequest);

	DataResult<CarResponse> getById(int id);
	
	public Car getCarById(int carId);

}
