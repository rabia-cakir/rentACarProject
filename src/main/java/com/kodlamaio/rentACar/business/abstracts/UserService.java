package com.kodlamaio.rentACar.business.abstracts;

import java.util.List;

import org.springframework.data.domain.Page;

import com.kodlamaio.rentACar.business.requests.users.CreateUserRequest;
import com.kodlamaio.rentACar.business.requests.users.UpdateUserRequest;
import com.kodlamaio.rentACar.business.responses.users.UserResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.entities.concretes.User;

public interface UserService {
	
	DataResult<List<UserResponse>> getAll();
	Result add(CreateUserRequest createUserRequest);
	Result update(UpdateUserRequest updateUserRequest, int id);
	Result delete(int id);
	Page<User> findPaginated(int pageNo, int pageSize);

}
