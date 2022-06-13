package com.kodlamaio.rentACar.business.responses.additionalServiceItems;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdditionalServiceItemResponse {
	private int id;
	private String name;
	private double price;

}
