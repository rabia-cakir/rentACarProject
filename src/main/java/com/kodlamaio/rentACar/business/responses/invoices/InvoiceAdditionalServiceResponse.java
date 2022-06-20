package com.kodlamaio.rentACar.business.responses.invoices;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceAdditionalServiceResponse {
	private int id;
	private int additionalServiceId;
	private String name;
	private double price;
	private String userName;
	private String userLastName;

}
