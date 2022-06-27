package com.kodlamaio.rentACar.business.concretes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.AdditionalServiceItemService;
import com.kodlamaio.rentACar.business.requests.additionalServiceItemRequests.CreateAdditionalServiceItemRequest;
import com.kodlamaio.rentACar.business.requests.additionalServiceItemRequests.UpdateAdditionalServiceItemRequest;
import com.kodlamaio.rentACar.business.responses.additionalServiceItemResponses.AdditionalServiceItemResponse;
import com.kodlamaio.rentACar.core.utilities.exceptions.BusinessException;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.AdditionalServiceItemRepository;
import com.kodlamaio.rentACar.entities.concretes.AdditionalServiceItem;

@Service
public class AdditionalServiceItemManager implements AdditionalServiceItemService {

	private AdditionalServiceItemRepository additionalServiceItemRepository;
	private ModelMapperService modelMapperService;

	public AdditionalServiceItemManager(AdditionalServiceItemRepository additionalServiceItemRepository,
			ModelMapperService modelMapperService) {
		super();
		this.additionalServiceItemRepository = additionalServiceItemRepository;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public DataResult<List<AdditionalServiceItemResponse>> getAll() {
		List<AdditionalServiceItem> additionalServiceItems = additionalServiceItemRepository.findAll();
		List<AdditionalServiceItemResponse> additionalServiceItemResponses = additionalServiceItems.stream()
				.map(additionalServiceItem -> modelMapperService.forResponse().map(additionalServiceItem,
						AdditionalServiceItemResponse.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<AdditionalServiceItemResponse>>(additionalServiceItemResponses,"DATA.LISTED.SUCCESSFULLY");
	}

	@Override
	public DataResult<AdditionalServiceItemResponse> findById(int id) {
		checkIfAdditionalServiceItemExistById(id);
		AdditionalServiceItem additionalServiceItem = additionalServiceItemRepository.findById(id).get();
		AdditionalServiceItemResponse additionalServiceItemResponse = modelMapperService.forResponse()
				.map(additionalServiceItem, AdditionalServiceItemResponse.class);
		return new SuccessDataResult<AdditionalServiceItemResponse>(additionalServiceItemResponse);
	}

	@Override
	public Result add(CreateAdditionalServiceItemRequest createAdditionalServiceItemRequest) {
		checkIfAdditionalServiceItemExistByName(createAdditionalServiceItemRequest.getName());
		AdditionalServiceItem additionalServiceItem = modelMapperService.forRequest()
				.map(createAdditionalServiceItemRequest, AdditionalServiceItem.class);
		additionalServiceItemRepository.save(additionalServiceItem);
		return new SuccessResult("ADDITIONAL.SERVICE.ITEM.ADDED");
	}

	@Override
	public Result update(UpdateAdditionalServiceItemRequest updateAdditionalServiceItemRequest) {
		checkIfAdditionalServiceItemExistById(updateAdditionalServiceItemRequest.getId());
		checkIfAdditionalServiceItemExistByName(updateAdditionalServiceItemRequest.getName());
		AdditionalServiceItem additionalServiceItem = modelMapperService.forRequest()
				.map(updateAdditionalServiceItemRequest, AdditionalServiceItem.class);
		additionalServiceItemRepository.save(additionalServiceItem);
		return new SuccessResult("ADDITIONAL.SERVICE.ITEM.UPDATED");
	}

	@Override
	public Result delete(int id) {
		checkIfAdditionalServiceItemExistById(id);
		additionalServiceItemRepository.deleteById(id);
		return new SuccessResult("ADDITIONAL.SERVICE.ITEM.DELETED");
	}

	private void checkIfAdditionalServiceItemExistByName(String name) {
		AdditionalServiceItem additionalServiceItem = additionalServiceItemRepository.findByName(name);
		if (additionalServiceItem != null)
			throw new BusinessException("ADDITIONAL.SERVICE.ITEM.EXIST");
	}

	private void checkIfAdditionalServiceItemExistById(int id) {
		Optional<AdditionalServiceItem> additionalServiceItem = additionalServiceItemRepository.findById(id);
		if (additionalServiceItem.isEmpty())
			throw new BusinessException("ADDITIONAL.SERVICE.ITEM.IS.NOT.EXIST");
	}

}
