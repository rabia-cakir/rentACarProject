package com.kodlamaio.rentACar.business.concretes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.AdditionalServiceService;
import com.kodlamaio.rentACar.business.requests.additionalServiceRequests.CreateAdditionalServiceRequest;
import com.kodlamaio.rentACar.business.requests.additionalServiceRequests.UpdateAdditionalServiceRequest;
import com.kodlamaio.rentACar.business.responses.additionalServices.AdditionalServiceResponse;
import com.kodlamaio.rentACar.core.utilities.exceptions.BusinessException;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.AdditionalServiceRepository;
import com.kodlamaio.rentACar.entities.concretes.AdditionalService;

@Service
public class AdditionalServiceManager implements AdditionalServiceService {

	private AdditionalServiceRepository additionalServiceRepository;
	private ModelMapperService modelMapperService;

	public AdditionalServiceManager(AdditionalServiceRepository additionalServiceRepository,
			ModelMapperService modelMapperService) {
		super();
		this.additionalServiceRepository = additionalServiceRepository;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public DataResult<List<AdditionalServiceResponse>> findAllByRentalId(int rentalId) {
		List<AdditionalService> additionalServices = additionalServiceRepository.findAllByRentalId(rentalId);
		List<AdditionalServiceResponse> additionalServiceResponse = additionalServices.stream()
				.map(additionalService -> modelMapperService.forResponse().map(additionalService,
						AdditionalServiceResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<AdditionalServiceResponse>>(additionalServiceResponse);
	}

	@Override
	public Result add(CreateAdditionalServiceRequest createAdditionalServiceRequest) {
		AdditionalService additionalService = modelMapperService.forRequest().map(createAdditionalServiceRequest,
				AdditionalService.class);
		additionalServiceRepository.save(additionalService);
		return new SuccessResult();
	}

	@Override
	public Result update(UpdateAdditionalServiceRequest updateAdditionalServiceRequest) {
		AdditionalService additionalService = modelMapperService.forRequest().map(updateAdditionalServiceRequest,
				AdditionalService.class);
		additionalServiceRepository.save(additionalService);
		return new SuccessResult();
	}

	@Override
	public Result delete(int id) {
		checkIfAdditionalServiceExistById(id);
		additionalServiceRepository.deleteById(id);
		return new SuccessResult();
	}

	private void checkIfAdditionalServiceExistById(int id) {
		Optional<AdditionalService> additionalService = additionalServiceRepository.findById(id);
		if (additionalService.isEmpty())
			throw new BusinessException("ADDITIONAL.SERVICE.IS.NOT.EXIST");
	}

}
