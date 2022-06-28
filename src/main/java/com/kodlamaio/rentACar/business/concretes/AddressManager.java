package com.kodlamaio.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.AddressService;
import com.kodlamaio.rentACar.business.requests.addressRequests.CreateAddressRequest;
import com.kodlamaio.rentACar.business.requests.addressRequests.UpdateAddressRequest;
import com.kodlamaio.rentACar.business.responses.addressResponses.AddressResponse;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.AddressRepository;
import com.kodlamaio.rentACar.entities.concretes.Address;

@Service
public class AddressManager implements AddressService {
	private AddressRepository addressRepository;
	private ModelMapperService modelMapperService;

	
	
	public AddressManager(AddressRepository addressRepository, ModelMapperService modelMapperService) {
		super();
		this.addressRepository = addressRepository;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public DataResult<List<AddressResponse>> getAll() {
		List<Address> getAllAddressesResponses = this.addressRepository.findAll();

		List<AddressResponse> response = getAllAddressesResponses.stream()
				.map(address -> this.modelMapperService.forResponse().map(address, AddressResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<AddressResponse>>(response,"DATA.LISTED.SUCCESSFULLY");
	}

	@Override
	public Result add(CreateAddressRequest createAddressRequest) {
		Address address = this.modelMapperService.forRequest().map(createAddressRequest, Address.class);
		this.addressRepository.save(address);
		return new SuccessResult("ADDRESS.ADDED");
	}

	@Override
	public Result update(UpdateAddressRequest updateAddressRequest) {
		Address address = this.modelMapperService.forRequest().map(updateAddressRequest, Address.class);
		this.addressRepository.save(address);
		return new SuccessResult("ADDRESS.UPDATED");
	}

	@Override
	public Result delete(int id) {
		addressRepository.deleteById(id);
		return new SuccessResult("ADDRESS.DELETED");
	}

	@Override
	public DataResult<List<AddressResponse>> getAllBillAddress(int customerId, int addressType) {
		List<Address> addresses = this.addressRepository.getByCustomerIdAndAddressType(customerId, addressType);
		List<AddressResponse> response = addresses.stream()
				.map(address -> this.modelMapperService.forResponse().map(address, AddressResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<AddressResponse>>(response);
	}

	@Override
	public DataResult<List<AddressResponse>> getAllContactAddress(int customerId, int addressType) {
		List<Address> addresses = this.addressRepository.getByCustomerIdAndAddressType(customerId, addressType);
		List<AddressResponse> response = addresses.stream()
				.map(address -> this.modelMapperService.forResponse().map(address, AddressResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<AddressResponse>>(response);
	}

}
