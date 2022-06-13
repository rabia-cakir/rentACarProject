package com.kodlamaio.rentACar.business.requests.cars;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.kodlamaio.rentACar.entities.concretes.Brand;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//validation daha çok verinin formatıyla ilgilenir.
@Data
@AllArgsConstructor
@NoArgsConstructor

public class CreateCarRequest {
	
	@NotBlank
	@Size(min=2,max=50)
	private String description;
	@Min(10)
	private double dailyPrice;
	private String licensePlate;
	private int kilometer;
	private int state;
	private int brandId;
	private int colorId;
	

}
