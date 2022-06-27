package com.kodlamaio.rentACar.business.abstracts;

import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.entities.concretes.Customer;

public interface CustomerService {
	DataResult<Customer> findCustomerById(int id);
}
