package com.kodlamaio.rentACar.business.responses.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
	private int id;
	 private String name;
	 private String lastName;
	 private String identityNumber;
	 private String email;
	 private String password;
}
