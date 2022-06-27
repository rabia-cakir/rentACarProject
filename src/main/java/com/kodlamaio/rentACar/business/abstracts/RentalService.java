package com.kodlamaio.rentACar.business.abstracts;

import java.util.List;

import com.kodlamaio.rentACar.business.requests.rentalRequests.CreateRentalRequest;
import com.kodlamaio.rentACar.business.requests.rentalRequests.UpdateRentalRequest;
import com.kodlamaio.rentACar.business.responses.rentalResponses.RentalResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;

public interface RentalService {

	Result addForIndividualCustomer(CreateRentalRequest createRentalRequest);
	
	Result addForCorporateCustomer(CreateRentalRequest createRentalRequest);

	DataResult<List<RentalResponse>> getAll();

	Result delete(int id);

	Result update(UpdateRentalRequest updateRentalRequest);
}
