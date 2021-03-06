package com.kodlamaio.rentACar.business.responses.maintenanceResponses;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaintenanceResponse {
	private int id;
	private LocalDate dateSent;
	private LocalDate dateReturned;
	private int carId;

}
