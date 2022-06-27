package com.kodlamaio.rentACar.business.requests.invoiceRequests;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateInvoiceRequest {
	private LocalDate invoicedDate;
	private int rentalId;

}
