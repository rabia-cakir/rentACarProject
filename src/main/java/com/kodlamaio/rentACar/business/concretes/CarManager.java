package com.kodlamaio.rentACar.business.concretes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.BrandService;
import com.kodlamaio.rentACar.business.abstracts.CarService;
import com.kodlamaio.rentACar.business.requests.cars.CreateCarRequest;
import com.kodlamaio.rentACar.dataAccess.abstracts.CarRepository;
import com.kodlamaio.rentACar.entities.concretes.Brand;
import com.kodlamaio.rentACar.entities.concretes.Car;
import com.kodlamaio.rentACar.entities.concretes.Color;


@Service
public class CarManager implements CarService {
	private CarRepository carRepository;
	

	@Autowired
	public CarManager(CarRepository carRepository) {
		
		this.carRepository = carRepository;

	}

	@Override
	public void add(CreateCarRequest createCarRequest) {
		// TODO Auto-generated method stub

		Car car=new Car();
		car.setDescription(createCarRequest.getDescription());
		car.setDailyPrice(createCarRequest.getDailyPrice());
		
		Brand brand=new Brand();
		brand.setId(createCarRequest.getBrandId());
		car.setBrand(brand);
		
		
		Color color=new Color();
		color.setId(createCarRequest.getColorId());
		car.setColor(color);
		
		this.carRepository.save(car);
		
		
	}

	@Override
	public List<CreateCarRequest> getAll() {
		
		List<Car> cars=carRepository.findAll();
		List<CreateCarRequest> carRequests=new ArrayList<CreateCarRequest>();
		CreateCarRequest carRequest=new CreateCarRequest();
		for(Car car:cars)
		{
			carRequest.setDailyPrice(car.getDailyPrice());
			carRequest.setDescription(car.getDescription());
			carRequest.setBrandId(car.getBrand().getId());
			carRequest.setColorId(car.getColor().getId());
		}
		
		return carRequests;
	}

	@Override
	public void deleteById(int id) {
		carRepository.deleteById(id);
		
	}

	@Override
	public void update(CreateCarRequest createCarRequest, int id) {
		Car car=new Car();
		car.setDescription(createCarRequest.getDescription());
		car.setDailyPrice(createCarRequest.getDailyPrice());
		
		Brand brand=new Brand();
		brand.setId(createCarRequest.getBrandId());
		car.setBrand(brand);
		
		
		Color color=new Color();
		color.setId(createCarRequest.getColorId());
		car.setColor(color);
		
		Optional<Car> currentCar=carRepository.findById(id);
		if(currentCar.isPresent())
		{
			Car foundCar=currentCar.get();
			foundCar.setBrand(car.getBrand());
			foundCar.setColor(car.getColor());
			foundCar.setDailyPrice(car.getDailyPrice());
			foundCar.setDescription(car.getDescription());
		}
		
	}
	
	

}
