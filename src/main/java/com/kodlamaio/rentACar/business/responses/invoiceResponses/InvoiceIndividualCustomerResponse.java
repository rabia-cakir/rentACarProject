package com.kodlamaio.rentACar.business.responses.invoiceResponses;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceIndividualCustomerResponse {
	private int id;
	private int rentalId;
	private LocalDate invoicedDate;
	private LocalDate pickUpDate;
	private LocalDate returnDate;
	private double totalPrice;
	private String identityNumber;
	private String firstName;
	private String lastName;
	private String email;
}
