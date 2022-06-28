package com.kodlamaio.rentACar.business.responses.addressResponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponse {

	private int id;
	private String address;
	private int customerId;
	private int addressType;
}
