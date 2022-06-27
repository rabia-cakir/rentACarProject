package com.kodlamaio.rentACar.business.abstracts;

import java.util.List;

import com.kodlamaio.rentACar.business.requests.cityRequests.CreateCityRequest;
import com.kodlamaio.rentACar.business.requests.cityRequests.UpdateCityRequest;
import com.kodlamaio.rentACar.business.responses.cityResponses.CityResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;

public interface CityService {
	Result add(CreateCityRequest createCityRequest);

	DataResult<CityResponse> findById(int id);

	DataResult<List<CityResponse>> getAll();

	Result update(UpdateCityRequest updateCityRequest);

	Result delete(int id);
}
