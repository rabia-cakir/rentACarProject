package com.kodlamaio.rentACar.business.abstracts;

import java.util.List;

import com.kodlamaio.rentACar.business.requests.additionalServiceItemRequests.CreateAdditionalServiceItemRequest;
import com.kodlamaio.rentACar.business.requests.additionalServiceItemRequests.UpdateAdditionalServiceItemRequest;
import com.kodlamaio.rentACar.business.responses.additionalServiceItems.AdditionalServiceItemResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;

public interface AdditionalServiceItemService {

	Result add(CreateAdditionalServiceItemRequest createAdditionalServiceItemRequest);

	Result update(UpdateAdditionalServiceItemRequest updateAdditionalServiceItemRequest);

	Result delete(int id);

	DataResult<AdditionalServiceItemResponse> findById(int id);

	DataResult<List<AdditionalServiceItemResponse>> getAll();

}
