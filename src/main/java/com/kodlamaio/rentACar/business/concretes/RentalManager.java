package com.kodlamaio.rentACar.business.concretes;

import java.time.LocalDate;
import java.time.Period;

import com.kodlamaio.rentACar.business.abstracts.CarService;
import com.kodlamaio.rentACar.business.abstracts.RentalService;
import com.kodlamaio.rentACar.business.requests.rentals.CreateRentalRequest;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.RentalRepository;
import com.kodlamaio.rentACar.entities.concretes.Car;
import com.kodlamaio.rentACar.entities.concretes.Rental;

public class RentalManager implements RentalService {

	private RentalRepository rentalRepository;
	private CarService carService;

	public RentalManager(RentalRepository rentalRepository, CarService carService) {
		super();
		this.rentalRepository = rentalRepository;
		this.carService = carService;
	}

	@Override
	public Result add(CreateRentalRequest createRentalRequest) {
		// mapping
		Rental rental = new Rental();
		Car car = carService.getById(createRentalRequest.getCarId());
		car.setState(3);
		rental.setPickUpDate(createRentalRequest.getPickUpDate());
		rental.setReturnDate(createRentalRequest.getReturnDate());
		
		LocalDate pickupDate = createRentalRequest.getPickUpDate();
		LocalDate returnDate = createRentalRequest.getReturnDate();
		Period period = Period.between(pickupDate, returnDate);
		int diff = Math.abs(period.getDays());
		rental.setTotalDays(diff);
		rental.setTotalPrice(createRentalRequest.getTotalDays() * car.getDailyPrice());
		rentalRepository.save(rental);
		
		return new SuccessResult();
	}

}
