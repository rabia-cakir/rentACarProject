package com.kodlamaio.rentACar.business.requests.userRequests.individualCustomerRequests;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CreateIndividualCustomerRequest {
	private String identityNumber;
	private String firstName;
	private String lastName;
	private LocalDate birthDate;
	private String email;
	private String password;

}
