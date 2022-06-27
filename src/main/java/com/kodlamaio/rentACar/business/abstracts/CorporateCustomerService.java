package com.kodlamaio.rentACar.business.abstracts;

import java.util.List;

import com.kodlamaio.rentACar.business.requests.userRequests.corporateCustomerRequests.CreateCorporateCustomerRequest;
import com.kodlamaio.rentACar.business.requests.userRequests.corporateCustomerRequests.UpdateCorporateCustomerRequest;
import com.kodlamaio.rentACar.business.responses.userResponses.CorporateCustomerResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;

public interface CorporateCustomerService {
	DataResult<List<CorporateCustomerResponse>> getAll();

	DataResult<CorporateCustomerResponse> findById(int id);

	Result add(CreateCorporateCustomerRequest createCorporateCustomerRequest);

	Result update(UpdateCorporateCustomerRequest updateCorporateCustomerRequest);

	Result delete(int id);

}
