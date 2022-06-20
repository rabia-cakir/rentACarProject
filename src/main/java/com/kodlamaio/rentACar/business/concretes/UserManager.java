package com.kodlamaio.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.UserService;
import com.kodlamaio.rentACar.business.requests.users.CreateUserRequest;
import com.kodlamaio.rentACar.business.requests.users.UpdateUserRequest;
import com.kodlamaio.rentACar.business.responses.users.UserResponse;
import com.kodlamaio.rentACar.core.utilities.adapters.abstracts.CheckUserService;
import com.kodlamaio.rentACar.core.utilities.exceptions.BusinessException;
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
	private CheckUserService checkUser;

	@Autowired
	public UserManager(UserRepository userRepository, ModelMapperService modelMapperService,
			CheckUserService checkUser) {
		super();
		this.userRepository = userRepository;
		this.modelMapperService = modelMapperService;
		this.checkUser = checkUser;
	}

	@Override
	public DataResult<List<UserResponse>> getAll() {
		List<User> users = userRepository.findAll();
		List<UserResponse> userResponses = users.stream()
				.map(user -> modelMapperService.forResponse().map(user, UserResponse.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<UserResponse>>(userResponses,"DATA.LISTED.SUCCESSFULLY");
	}

	@Override
	public Result add(CreateUserRequest createUserRequest) {

		User user = modelMapperService.forRequest().map(createUserRequest, User.class);
		checkUserExistsByNationality(user);
		userRepository.save(user);
		return new SuccessResult("USER.ADDED");
	}

	@Override
	public Result update(UpdateUserRequest updateUserRequest) {
		User user = modelMapperService.forRequest().map(updateUserRequest, User.class);
		userRepository.save(user);
		return new SuccessResult("USER.UPDATED");
	}

	@Override
	public Result delete(int id) {
		userRepository.deleteById(id);
		return new SuccessResult("USER.DELETED");
	}

	@Override
	public Page<User> findPaginated(int pageNo, int pageSize) {
		// the page count starts at zero
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		return userRepository.findAll(pageable);
	}

	private void checkUserExistsByNationality(User user) {

		if (!checkUser.checkIfUserExist(user)) {

			throw new BusinessException("USER.IS.NOT.VALID");
		}

	}
}
