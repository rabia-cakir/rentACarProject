package com.kodlamaio.rentACar.api;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentACar.business.abstracts.CarService;
import com.kodlamaio.rentACar.business.requests.cars.CreateCarRequest;

@RestController
@RequestMapping("/api/cars")
public class CarsController {
	
	private CarService carService;
	
	public CarsController(CarService carService)
	{
		this.carService=carService;
	}
	
	@PostMapping("/add")
	public void add(@RequestBody CreateCarRequest createCarRequest)
	{
		carService.add(createCarRequest);
	}
	
	@GetMapping("/getall")
	public List<CreateCarRequest> getAll()
	{
		return carService.getAll();
	}


	@DeleteMapping("/{id}")
	public void delete(@PathVariable int id)
	{
		carService.deleteById(id);
	}
	
	@PutMapping("/{id}")
	public void update(@PathVariable int id, @RequestBody CreateCarRequest createCarRequest )
	{
		carService.update(createCarRequest, id);
	}
}
