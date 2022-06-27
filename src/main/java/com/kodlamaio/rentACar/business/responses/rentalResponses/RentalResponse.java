package com.kodlamaio.rentACar.business.responses.rentalResponses;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentalResponse {
	private int id;
	private LocalDate pickUpDate;
	private LocalDate returnDate;
	private int totalDays;
	private double totalPrice;
	private int carId;
	private int pickUpCityId;
	private int returnCityId;
	private int customerId;
}
