package com.kodlamaio.rentACar.business.concretes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.BrandService;
import com.kodlamaio.rentACar.business.abstracts.CarService;
import com.kodlamaio.rentACar.business.abstracts.ColorService;
import com.kodlamaio.rentACar.business.requests.cars.CreateCarRequest;
import com.kodlamaio.rentACar.business.requests.cars.UpdateCarRequest;
import com.kodlamaio.rentACar.business.responses.cars.CarResponse;
import com.kodlamaio.rentACar.core.utilities.exceptions.BusinessException;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.ErrorResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.CarRepository;
import com.kodlamaio.rentACar.entities.concretes.Brand;
import com.kodlamaio.rentACar.entities.concretes.Car;
import com.kodlamaio.rentACar.entities.concretes.Color;

@Service
public class CarManager implements CarService {
	private CarRepository carRepository;
	private ColorService colorService;
	private BrandService brandService;
	private ModelMapperService modelMapperService;

	@Autowired
	public CarManager(CarRepository carRepository, ModelMapperService modelMapperService) {

		this.carRepository = carRepository;
		this.colorService = colorService;
		this.brandService = brandService;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public Result add(CreateCarRequest createCarRequest) {

		// Color color=colorService.getColorById(createCarRequest.getColorId());
		// Brand brand=brandService.getBrandById(createCarRequest.getBrandId());
		// if(color==null || brand==null)
		// return new ErrorResult("CAR.ERROR");
		
		Car car1=this.modelMapperService.forRequest().map(createCarRequest, Car.class);		
		
	/*	Car car = new Car();
		car.setDescription(createCarRequest.getDescription());
		car.setDailyPrice(createCarRequest.getDailyPrice());
		car.setKilometer(createCarRequest.getKilometer());
		car.setLicensePlate(createCarRequest.getLicensePlate());
		car.setState(createCarRequest.getState());
		
		Brand brand = new Brand();
		brand.setId(createCarRequest.getBrandId());

		Color color = new Color();
		color.setId(createCarRequest.getColorId());

		car.setBrand(brand);
		car.setColor(color);*/
		checkNumberOfCars(car1.getBrand().getId());
		carRepository.save(car1);
		return new SuccessResult();
	}

	@Override
	public List<CarResponse> getAll() {

		List<Car> cars = carRepository.findAll();
		// return cars.stream().map(c->new CarResponse(c)).collect(Collectors.toList());
		return cars.stream().map(car -> this.modelMapperService.forResponse().map(car, CarResponse.class))
				.collect(Collectors.toList());
	}

	@Override
	public void deleteById(int id) {
		carRepository.deleteById(id);

	}

	@Override
	public void update(UpdateCarRequest updateCarRequest, int id) {

		Car car=modelMapperService.forRequest().map(updateCarRequest, Car.class);

		Optional<Car> currentCar = carRepository.findById(id);
		if (currentCar.isPresent()) {

			Car foundCar = currentCar.get();

			foundCar.setDailyPrice(car.getDailyPrice());
			foundCar.setDescription(car.getDescription());
			foundCar.setKilometer(car.getKilometer());
			foundCar.setLicensePlate(car.getLicensePlate());
			foundCar.setState(car.getState());

			// foundCar=modelMapperService.forRequest().map(updateCarRequest, Car.class);
			carRepository.save(foundCar);
		}

	}

	public Car getById(int id) {
		return carRepository.findById(id).get();
	}

	
	private void checkNumberOfCars(int id)
	{
		List<Car> existsCars = carRepository.getByBrandId(id);
		if (existsCars.size() > 5) 
			throw new BusinessException("CAR.NUMBER.IS.HIGHER.THAN.FIVE");
		
	}
}
