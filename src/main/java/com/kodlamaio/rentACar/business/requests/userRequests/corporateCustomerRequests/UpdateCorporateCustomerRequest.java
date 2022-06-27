package com.kodlamaio.rentACar.business.requests.userRequests.corporateCustomerRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCorporateCustomerRequest {
	
	private int id;
	private String companyName;
	private String email;
	private String password;

}
