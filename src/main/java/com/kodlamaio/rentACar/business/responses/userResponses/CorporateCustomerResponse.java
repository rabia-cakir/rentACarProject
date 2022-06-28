package com.kodlamaio.rentACar.business.responses.userResponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CorporateCustomerResponse {
	private int id;
	private String companyName;

}
