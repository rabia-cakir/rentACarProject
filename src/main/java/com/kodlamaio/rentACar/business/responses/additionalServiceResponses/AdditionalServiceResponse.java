package com.kodlamaio.rentACar.business.responses.additionalServiceResponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdditionalServiceResponse {
	
	private int id;
	private int rentalId;
	private int additionalServiceItemId;

}
