package com.kodlamaio.rentACar.business.abstracts;

import com.kodlamaio.rentACar.business.requests.rentals.CreateRentalRequest;
import com.kodlamaio.rentACar.core.utilities.results.Result;

public interface RentalService {
	
	Result add(CreateRentalRequest createRentalRequest);

}
