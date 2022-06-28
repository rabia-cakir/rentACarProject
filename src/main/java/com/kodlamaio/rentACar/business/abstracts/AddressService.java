package com.kodlamaio.rentACar.business.abstracts;

import java.util.List;

import com.kodlamaio.rentACar.business.requests.addressRequests.CreateAddressRequest;
import com.kodlamaio.rentACar.business.requests.addressRequests.UpdateAddressRequest;
import com.kodlamaio.rentACar.business.responses.addressResponses.AddressResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;

public interface AddressService {
	DataResult<List<AddressResponse>> getAll();
	Result add(CreateAddressRequest createAddressRequest);
	Result update(UpdateAddressRequest updateAddressRequest);
	Result delete(int id);
	DataResult<List<AddressResponse>> getAllBillAddress(int customerId, int addressType);
	DataResult<List<AddressResponse>> getAllContactAddress(int customerId, int addressType);

}
