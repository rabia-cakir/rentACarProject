package com.kodlamaio.rentACar.business.abstracts;

import java.util.List;

import com.kodlamaio.rentACar.business.requests.additionalServiceRequests.CreateAdditionalServiceRequest;
import com.kodlamaio.rentACar.business.requests.additionalServiceRequests.UpdateAdditionalServiceRequest;
import com.kodlamaio.rentACar.business.responses.additionalServices.AdditionalServiceResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;

public interface AdditionalServiceService {
	
	DataResult<List<AdditionalServiceResponse>> findAllByRentalId(int rentalId);
	Result add(CreateAdditionalServiceRequest createAdditionalServiceRequest);
	Result update(UpdateAdditionalServiceRequest updateAdditionalServiceRequest);
	Result delete(int id);

}
