package com.kodlamaio.rentACar.business.requests.addressRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAddressRequest {
	private int id;
	private String address;
	private int customerId;
	private int addressType;


}
