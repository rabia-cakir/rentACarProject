package com.kodlamaio.rentACar.business.concretes;

import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.RentalService;
import com.kodlamaio.rentACar.business.requests.rentals.CreateRentalRequest;
import com.kodlamaio.rentACar.business.requests.rentals.UpdateRentalRequest;
import com.kodlamaio.rentACar.business.responses.rentals.RentalResponse;
import com.kodlamaio.rentACar.core.utilities.adapters.abstracts.CheckFindexService;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.CarRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.CityRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.RentalRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.UserRepository;
import com.kodlamaio.rentACar.entities.concretes.Car;
import com.kodlamaio.rentACar.entities.concretes.City;
import com.kodlamaio.rentACar.entities.concretes.Rental;
import com.kodlamaio.rentACar.entities.concretes.User;

@Service
public class RentalManager implements RentalService {

	private RentalRepository rentalRepository;
	private CarRepository carRepository;
	private ModelMapperService modelMapperService;
	private CheckFindexService checkFindexService;
	private UserRepository userRepository;
	private CityRepository cityRepository;

	public RentalManager(RentalRepository rentalRepository, ModelMapperService modelMapperService,
			CheckFindexService checkFindexService, CarRepository carRepository, UserRepository userRepository,
			CityRepository cityRepository) {
		super();
		this.rentalRepository = rentalRepository;
		this.carRepository = carRepository;
		this.modelMapperService = modelMapperService;
		this.checkFindexService = checkFindexService;
		this.userRepository = userRepository;
		this.cityRepository = cityRepository;

	}

	@Override
	public Result add(CreateRentalRequest createRentalRequest) {

		Rental rental = new Rental();
		Car car = carRepository.getById(createRentalRequest.getCarId());
		City pickUpCity = cityRepository.findById(createRentalRequest.getPickUpCityId()).get();
		City returnCity = cityRepository.findById(createRentalRequest.getReturnCityId()).get();
		User user = userRepository.findById(createRentalRequest.getUserId()).get();
		car.setState(3);
		rental.setPickUpDate(createRentalRequest.getPickUpDate());
		rental.setReturnDate(createRentalRequest.getReturnDate());
		rental.setCar(car);
		rental.setPickUpCity(pickUpCity);
		rental.setReturnCity(returnCity);
		Period period = Period.between(createRentalRequest.getPickUpDate(), createRentalRequest.getReturnDate());
		int diff = Math.abs(period.getDays());
		rental.setTotalDays(diff);
		if (!pickUpCity.equals(returnCity))
			rental.setTotalPrice(rental.getTotalDays() * car.getDailyPrice() + 750);

		else
			rental.setTotalPrice(rental.getTotalDays() * car.getDailyPrice());

		if (checkFindexMinValue(car.getMinFindexScore(), user.getIdentityNumber())) {
			rentalRepository.save(rental);
			return new Result(true, "RENTAL.ADDED");
		} else {
			return new Result(false, "FINDEX.SCORE.IS.NOT.ENOUGH");
		}

	}

	@Override
	public DataResult<List<RentalResponse>> getAll() {
		List<Rental> rentals = rentalRepository.findAll();
		List<RentalResponse> response = rentals.stream()
				.map(rental -> this.modelMapperService.forResponse().map(rental, RentalResponse.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<>(response, "DATA.LISTED.SUCCESSFULLY");

	}

	public boolean checkFindexMinValue(int score, String identityNumber) {
		boolean state = false;
		if (checkFindexService.CheckFindexScore(identityNumber) > score) {
			state = true;
		} else {
			state = false;
		}
		return state;
	}

	@Override
	public Result delete(int id) {
		rentalRepository.deleteById(id);
		return new SuccessResult("RENTAL.DELETED");
	}

	@Override
	public Result update(UpdateRentalRequest updateRentalRequest) {

		Rental rental = new Rental();
		Car car = carRepository.getById(updateRentalRequest.getCarId());
		City pickUpCity = cityRepository.findById(updateRentalRequest.getPickUpCityId()).get();
		City returnCity = cityRepository.findById(updateRentalRequest.getReturnCityId()).get();
		User user = userRepository.findById(updateRentalRequest.getUserId()).get();
		car.setState(3);
		rental.setPickUpDate(updateRentalRequest.getPickUpDate());
		rental.setReturnDate(updateRentalRequest.getReturnDate());
		rental.setCar(car);
		rental.setPickUpCity(pickUpCity);
		rental.setReturnCity(returnCity);
		Period period = Period.between(updateRentalRequest.getPickUpDate(), updateRentalRequest.getReturnDate());
		int diff = Math.abs(period.getDays());
		rental.setTotalDays(diff);
		if (!pickUpCity.equals(returnCity))
			rental.setTotalPrice(rental.getTotalDays() * car.getDailyPrice() + 750);

		else
			rental.setTotalPrice(rental.getTotalDays() * car.getDailyPrice());

		if (checkFindexMinValue(car.getMinFindexScore(), user.getIdentityNumber())) {
			rentalRepository.save(rental);
			return new Result(true, "RENTAL.ADDED");
		} else {
			return new Result(false, "FINDEX.SCORE.IS.NOT.ENOUGH");
		}
	}

}
