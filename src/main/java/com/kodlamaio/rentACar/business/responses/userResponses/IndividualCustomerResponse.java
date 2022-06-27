package com.kodlamaio.rentACar.business.responses.userResponses;

import java.time.LocalDate;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class IndividualCustomerResponse {
	private int id;
	private String identityNumber;
	private String firstName;
	private String lastName;
	private LocalDate birthDate; 
	private String email;
	private String password;

}
