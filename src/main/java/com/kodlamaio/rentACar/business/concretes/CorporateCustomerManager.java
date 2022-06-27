package com.kodlamaio.rentACar.business.concretes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.CorporateCustomerService;
import com.kodlamaio.rentACar.business.requests.userRequests.corporateCustomerRequests.CreateCorporateCustomerRequest;
import com.kodlamaio.rentACar.business.requests.userRequests.corporateCustomerRequests.UpdateCorporateCustomerRequest;
import com.kodlamaio.rentACar.business.responses.userResponses.CorporateCustomerResponse;
import com.kodlamaio.rentACar.core.utilities.exceptions.BusinessException;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.CorporateCustomerRepository;
import com.kodlamaio.rentACar.entities.concretes.CorporateCustomer;

@Service
public class CorporateCustomerManager implements CorporateCustomerService {

	ModelMapperService modelMapperService;
	CorporateCustomerRepository corporateCustomerRepository;

	public CorporateCustomerManager(ModelMapperService modelMapperService,
			CorporateCustomerRepository corporateCustomerRepository) {
		super();
		this.modelMapperService = modelMapperService;
		this.corporateCustomerRepository = corporateCustomerRepository;
	}

	@Override
	public DataResult<List<CorporateCustomerResponse>> getAll() {
		List<CorporateCustomer> corporateCustomers=corporateCustomerRepository.findAll();
		List<CorporateCustomerResponse> response=corporateCustomers.stream().map(
				corporateCustomer -> modelMapperService.forResponse().map(corporateCustomer, CorporateCustomerResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<CorporateCustomerResponse>>(response,"DATA.LISTED.SUCCESSFULLY");
	}

	@Override
	public DataResult<CorporateCustomerResponse> findById(int id) {
		checkIfCorporateCustomerExistById(id);
		CorporateCustomer corporateCustomer=corporateCustomerRepository.findById(id).get();
		return new SuccessDataResult<CorporateCustomerResponse>(modelMapperService.forResponse().map(corporateCustomer, CorporateCustomerResponse.class));
	}

	@Override
	public Result add(CreateCorporateCustomerRequest createCorporateCustomerRequest) {
		checkIfCompanyNameExist(createCorporateCustomerRequest.getCompanyName());
		corporateCustomerRepository.save(modelMapperService.forRequest().map(createCorporateCustomerRequest, CorporateCustomer.class));
		return new SuccessDataResult<>("CORPORATE.CUSTOMER.ADDED");
	}

	@Override
	public Result update(UpdateCorporateCustomerRequest updateCorporateCustomerRequest) {
		checkIfCorporateCustomerExistById(updateCorporateCustomerRequest.getId());
		checkIfCompanyNameExist(updateCorporateCustomerRequest.getCompanyName());
		corporateCustomerRepository.save(modelMapperService.forRequest().map(updateCorporateCustomerRequest, CorporateCustomer.class));
		return new SuccessDataResult<>("CORPORATE.CUSTOMER.UPDATED");
	}

	@Override
	public Result delete(int id) {
		checkIfCorporateCustomerExistById(id);
		corporateCustomerRepository.deleteById(id);
		return new SuccessResult("CORPORATE.CUSTOMER.DELETED");
	}

	private void checkIfCorporateCustomerExistById(int id) {
		Optional<CorporateCustomer> corporateCustomer = corporateCustomerRepository.findById(id);
		if (corporateCustomer.isEmpty())
			throw new BusinessException("CORPORATE.CUSTOMER.IS.NOT.EXIST");

	}
	
	private void checkIfCompanyNameExist(String companyName)
	{
		CorporateCustomer corporateCustomer=corporateCustomerRepository.findByCompanyName(companyName);
		if(corporateCustomer!=null)
			throw new BusinessException("COMPANY.NAME.IS.ALREADY.EXIST");
	}

}
