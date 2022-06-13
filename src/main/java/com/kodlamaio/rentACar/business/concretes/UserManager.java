package com.kodlamaio.rentACar.business.concretes;



import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.UserService;
import com.kodlamaio.rentACar.business.requests.users.CreateUserRequest;
import com.kodlamaio.rentACar.business.requests.users.UpdateUserRequest;
import com.kodlamaio.rentACar.business.responses.users.UserResponse;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.UserRepository;
import com.kodlamaio.rentACar.entities.concretes.User;


@Service
public class UserManager implements UserService {
	private UserRepository userRepository;
	private ModelMapperService modelMapperService;
	
	
	

	public UserManager(UserRepository userRepository, ModelMapperService modelMapperService) {
		super();
		this.userRepository = userRepository;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public DataResult<List<UserResponse>> getAll() {
		List<User> users=userRepository.findAll();
		List<UserResponse> userResponses=users.stream().map(
				user-> modelMapperService.forResponse().map(user, UserResponse.class))
				.collect(Collectors.toList());
				
		return new SuccessDataResult<List<UserResponse>>(userResponses);
	}

	@Override
	public Result add(CreateUserRequest createUserRequest) {
		User user=modelMapperService.forRequest().map(createUserRequest, User.class);
		userRepository.save(user);
		return new SuccessResult();
	}

	@Override
	public Result update(UpdateUserRequest updateUserRequest, int id) {
		User user=modelMapperService.forRequest().map(updateUserRequest, User.class);
		User userToUpdate=userRepository.findById(id).get();
		userToUpdate.setIdentityNumber(user.getIdentityNumber());
		userToUpdate.setName(user.getName());
		userToUpdate.setLastName(user.getLastName());
		userToUpdate.setEmail(user.getEmail());
		userToUpdate.setPassword(user.getPassword());
		
		userRepository.save(userToUpdate);
		return new SuccessResult();
	}

	@Override
	public Result delete(int id) {
		userRepository.deleteById(id);
		return new SuccessResult();
	}

	@Override
	public Page<User> findPaginated(int pageNo, int pageSize) {
		//the page count starts at zero
		Pageable pageable=PageRequest.of(pageNo-1,pageSize);
		return userRepository.findAll(pageable);
	}

}
