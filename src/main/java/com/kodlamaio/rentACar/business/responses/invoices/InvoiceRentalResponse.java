package com.kodlamaio.rentACar.business.responses.invoices;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceRentalResponse {
	private int id;
	private int rentalId;
	 private int totalDays;
     private double totalPrice;
     private LocalDate pickUpDate;
     private LocalDate returnDate;
     private int  pickUpCityId;
     private int returnCityId;
     private String userName;
     private String userLastName;
}
