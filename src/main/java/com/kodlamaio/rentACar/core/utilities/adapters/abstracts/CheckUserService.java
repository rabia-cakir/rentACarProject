package com.kodlamaio.rentACar.core.utilities.adapters.abstracts;

import com.kodlamaio.rentACar.entities.concretes.IndividualCustomer;

public interface CheckUserService {
	boolean checkIfUserExist(IndividualCustomer individualCustomer);
}
