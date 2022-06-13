package com.kodlamaio.rentACar.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentACar.business.abstracts.UserService;
import com.kodlamaio.rentACar.business.requests.users.CreateUserRequest;
import com.kodlamaio.rentACar.business.requests.users.UpdateUserRequest;
import com.kodlamaio.rentACar.business.responses.users.UserResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.entities.concretes.User;

@RestController
@RequestMapping("/api/users")
public class UsersController {

	private UserService userService;

	public UsersController(UserService userService) {
		super();
		this.userService = userService;
	}

	@GetMapping("/getall")
	public DataResult<List<UserResponse>> getAll() {
		return userService.getAll();
	}

	@DeleteMapping("/{id}")
	public Result delete(@PathVariable int id) {
		return userService.delete(id);
	}

	@PostMapping("/add")
	public Result add(@RequestBody @Valid CreateUserRequest createUserRequest) {
		return userService.add(createUserRequest);
	}

	@PutMapping("/{id}")
	public Result update(@RequestBody UpdateUserRequest updateUserRequest, @PathVariable int id) {
		return userService.update(updateUserRequest, id);
	}

	@GetMapping("/page/{pageNo}")
	public DataResult<Page<User>> findPaginated(@PathVariable(value = "pageNo") int pageNo) {
		int pageSize = 5;
		Page<User> page = userService.findPaginated(pageNo, pageSize);
		// List<User> listUsers=page.getContent();
		return new SuccessDataResult<Page<User>>(page);

	}

}
