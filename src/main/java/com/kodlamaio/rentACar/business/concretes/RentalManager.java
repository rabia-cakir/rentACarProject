package com.kodlamaio.rentACar.business.concretes;

import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.CarService;
import com.kodlamaio.rentACar.business.abstracts.CityService;
import com.kodlamaio.rentACar.business.abstracts.RentalService;
import com.kodlamaio.rentACar.business.requests.rentals.CreateRentalRequest;
import com.kodlamaio.rentACar.business.responses.rentals.RentalResponse;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.RentalRepository;
import com.kodlamaio.rentACar.entities.concretes.Car;
import com.kodlamaio.rentACar.entities.concretes.City;
import com.kodlamaio.rentACar.entities.concretes.Rental;

@Service
public class RentalManager implements RentalService {

	private RentalRepository rentalRepository;
	private CarService carService;
	private ModelMapperService modelMapperService;
	private CityService cityService;

	public RentalManager(RentalRepository rentalRepository, CarService carService, ModelMapperService modelMapperService,CityService cityService) {
		super();
		this.rentalRepository = rentalRepository;
		this.carService = carService;
		this.modelMapperService = modelMapperService;
		this.cityService=cityService;
	}

	@Override
	public Result add(CreateRentalRequest createRentalRequest) {
		// mapping
		// Rental rental=this.modelMapperService.forRequest().map(createRentalRequest,
		// Rental.class);
		
		Rental rental = new Rental();
		Car car = carService.getById(createRentalRequest.getCarId());
		City pickUpCity=cityService.findById(createRentalRequest.getPickUpCityId());
		City returnCity=cityService.findById(createRentalRequest.getReturnCityId());
		car.setState(3);
		rental.setPickUpDate(createRentalRequest.getPickUpDate());
		rental.setReturnDate(createRentalRequest.getReturnDate());
		rental.setCar(car);
		rental.setPickUpCity(pickUpCity);
		rental.setReturnCity(returnCity);
		Period period = Period.between(createRentalRequest.getPickUpDate(), createRentalRequest.getReturnDate());
		int diff = Math.abs(period.getDays());
		rental.setTotalDays(diff);
		if(!pickUpCity.equals(returnCity))
			rental.setTotalPrice(rental.getTotalDays() * car.getDailyPrice()+750);
		
		else
			rental.setTotalPrice(rental.getTotalDays() * car.getDailyPrice());
		rentalRepository.save(rental);
		return new SuccessResult(); 
	}

	@Override
	public List<RentalResponse> getAll() {
		List<Rental> rentals=rentalRepository.findAll();
		return rentals.stream().map(rental->this.modelMapperService.forResponse().map(rental, RentalResponse.class)).collect(Collectors.toList());
		
		
	}

}
