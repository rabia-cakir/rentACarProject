package com.kodlamaio.rentACar.business.requests.invoices;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateInvoiceRentalRequest {
	private LocalDate invoicedDate;
	private int rentalId;

}
