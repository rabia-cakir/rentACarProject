package com.kodlamaio.rentACar.business.abstracts;

import java.util.List;

import com.kodlamaio.rentACar.business.requests.userRequests.individualCustomerRequests.CreateIndividualCustomerRequest;
import com.kodlamaio.rentACar.business.requests.userRequests.individualCustomerRequests.UpdateIndividualCustomerRequest;
import com.kodlamaio.rentACar.business.responses.userResponses.IndividualCustomerResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;

public interface IndividualCustomerService {

	DataResult<List<IndividualCustomerResponse>> getAll();

	DataResult<IndividualCustomerResponse> findById(int id);

	Result add(CreateIndividualCustomerRequest createIndividualCustomerRequest);

	Result update(UpdateIndividualCustomerRequest updateIndividualCustomerRequest);

	Result delete(int id);
	
	String getIdentityNumber(int customerId);

}
