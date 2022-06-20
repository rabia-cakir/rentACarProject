package com.kodlamaio.rentACar.business.abstracts;

import java.util.List;

import com.kodlamaio.rentACar.business.requests.rentals.CreateRentalRequest;
import com.kodlamaio.rentACar.business.requests.rentals.UpdateRentalRequest;
import com.kodlamaio.rentACar.business.responses.rentals.RentalResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;

public interface RentalService {

	Result add(CreateRentalRequest createRentalRequest);

	DataResult<List<RentalResponse>> getAll();

	Result delete(int id);

	Result update(UpdateRentalRequest updateRentalRequest);
}
