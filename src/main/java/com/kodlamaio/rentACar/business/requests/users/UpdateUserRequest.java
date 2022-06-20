package com.kodlamaio.rentACar.business.requests.users;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequest {
	 private String name;
	 private String lastName;
	 private String identityNumber;
	 private String email;
	 private String password;
	 private LocalDate birthDate;

}
