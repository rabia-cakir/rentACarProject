package com.kodlamaio.rentACar.business.concretes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.IndividualCustomerService;
import com.kodlamaio.rentACar.business.requests.userRequests.individualCustomerRequests.CreateIndividualCustomerRequest;
import com.kodlamaio.rentACar.business.requests.userRequests.individualCustomerRequests.UpdateIndividualCustomerRequest;
import com.kodlamaio.rentACar.business.responses.userResponses.IndividualCustomerResponse;
import com.kodlamaio.rentACar.core.utilities.adapters.abstracts.CheckUserService;
import com.kodlamaio.rentACar.core.utilities.exceptions.BusinessException;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.IndividualCustomerRepository;
import com.kodlamaio.rentACar.entities.concretes.IndividualCustomer;

@Service
public class IndividualCustomerManager implements IndividualCustomerService {
	
	private IndividualCustomerRepository individualCustomerRepository;
	private ModelMapperService modelMapperService;
	private CheckUserService checkUser;
	

	public IndividualCustomerManager(IndividualCustomerRepository individualCustomerRepository,
			ModelMapperService modelMapperService, CheckUserService checkUser ) {
		super();
		this.individualCustomerRepository = individualCustomerRepository;
		this.modelMapperService = modelMapperService;
		this.checkUser=checkUser;
		
	}

	@Override
	public DataResult<List<IndividualCustomerResponse>> getAll() {
		List<IndividualCustomer> individualCustomers=individualCustomerRepository.findAll();
		List<IndividualCustomerResponse> response=individualCustomers.stream().map(
				individualCustomer -> modelMapperService.forResponse().map(individualCustomer, IndividualCustomerResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<IndividualCustomerResponse>>(response, "DATA.LISTED.SUCCESSFULLY");
	}

	@Override
	public DataResult<IndividualCustomerResponse> findById(int id) {
		checkIfIndividualCustomerExistById(id);
		IndividualCustomer individualCustomer=individualCustomerRepository.findById(id).get();
		return new SuccessDataResult<IndividualCustomerResponse>(modelMapperService.forResponse().map(individualCustomer, IndividualCustomerResponse.class));
	}

	@Override
	public Result add(CreateIndividualCustomerRequest createIndividualCustomerRequest) {
		checkIfEmailExist(createIndividualCustomerRequest.getEmail());
		checkIfIdentityNumberExist(createIndividualCustomerRequest.getIdentityNumber());
		IndividualCustomer individualCustomer=modelMapperService.forRequest().map(createIndividualCustomerRequest, IndividualCustomer.class);
		checkUserExistsByNationality(individualCustomer);
		individualCustomerRepository.save(individualCustomer);
		return new SuccessResult("INDIVIDUAL.CUSTOMER.ADDED");
	}

	@Override
	public Result update(UpdateIndividualCustomerRequest updateIndividualCustomerRequest) {
		checkIfIndividualCustomerExistById(updateIndividualCustomerRequest.getId());
		checkIfEmailExist(updateIndividualCustomerRequest.getEmail());
		checkIfIdentityNumberExist(updateIndividualCustomerRequest.getIdentityNumber());
		individualCustomerRepository.save(modelMapperService.forRequest().map(updateIndividualCustomerRequest, IndividualCustomer.class));
		return null;
	}

	@Override
	public Result delete(int id) {
		checkIfIndividualCustomerExistById(id);
		individualCustomerRepository.deleteById(id);
		return new SuccessResult("INDIVIDUAL.CUSTOMER.DELETED");
	}
	
	private void checkIfEmailExist(String email)
	{
		IndividualCustomer individualCustomer=individualCustomerRepository.findByEmail(email);
		if(individualCustomer!=null)
			throw new BusinessException("EMAIL.IS.ALREADY.EXIST");
			
	}
	
	private void checkIfIdentityNumberExist(String identityNumber)
	{
		IndividualCustomer individualCustomer=individualCustomerRepository.findByIdentityNumber(identityNumber);
		if(individualCustomer!=null)
			throw new BusinessException("IDENTITY.NUMBER.IS.ALREADY.EXIST");
			
	}
	
	private void checkIfIndividualCustomerExistById(int id)
	{
		Optional<IndividualCustomer> individualCustomer=individualCustomerRepository.findById(id);
		if(individualCustomer.isEmpty())
			throw new BusinessException("INDIVIDUAL.CUSTOMER.IS.NOT.EXIST");
			
	}
	
	private void checkUserExistsByNationality(IndividualCustomer individualCustomer) {

		if (!checkUser.checkIfUserExist(individualCustomer)) {

			throw new BusinessException("USER.IS.NOT.VALID");
		}

	}

	@Override
	public String getIdentityNumber(int customerId) {
		return individualCustomerRepository.findById(customerId).get().getIdentityNumber();
	}
	
	

}
