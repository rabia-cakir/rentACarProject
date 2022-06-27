package com.kodlamaio.rentACar.business.concretes;

import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.CustomerService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.ErrorDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.CustomerRepository;
import com.kodlamaio.rentACar.entities.concretes.Customer;

@Service
public class CustomerManager implements CustomerService {

	private CustomerRepository customerRepository;

	public CustomerManager(CustomerRepository customerRepository) {
		super();
		this.customerRepository = customerRepository;
	}

	@Override
	public DataResult<Customer> findCustomerById(int id) {
		if(!customerRepository.existsById(id))
			return new ErrorDataResult<Customer>("CUSTOMER.NOT.FOUND");
		return new SuccessDataResult<Customer>();
	}

}
