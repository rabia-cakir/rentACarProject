package com.kodlamaio.rentACar.business.requests.rentals;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRentalRequest {
	private int id;
	private LocalDate pickUpDate;
	private LocalDate returnDate;
	private int carId;
	private int pickUpCityId;
	private int returnCityId;
	private int userId;
}
