package com.kodlamaio.rentACar.business.abstracts;

import java.util.List;

import com.kodlamaio.rentACar.business.requests.cars.CreateCarRequest;
import com.kodlamaio.rentACar.business.requests.cars.UpdateCarRequest;
import com.kodlamaio.rentACar.business.responses.cars.CarResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;

public interface CarService {
	Result add(CreateCarRequest createCarRequest);

	DataResult<List<CarResponse>> getAll();

	Result deleteById(int id);

	Result update(UpdateCarRequest updateCarRequest);

	DataResult<CarResponse> getById(int id);

}
