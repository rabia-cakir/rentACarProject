package com.kodlamaio.rentACar.business.responses.invoiceResponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceAdditionalServiceResponse {
	private int id;
	private String name;
	private double price;
	private String customerName;
	private String customerLastName;

}
