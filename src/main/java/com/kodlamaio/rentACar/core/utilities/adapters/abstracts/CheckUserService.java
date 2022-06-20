package com.kodlamaio.rentACar.core.utilities.adapters.abstracts;

import com.kodlamaio.rentACar.entities.concretes.User;

public interface CheckUserService {
	boolean checkIfUserExist(User user);
}
