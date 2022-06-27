package com.kodlamaio.rentACar.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentACar.business.abstracts.CarService;
import com.kodlamaio.rentACar.business.requests.carRequests.CreateCarRequest;
import com.kodlamaio.rentACar.business.requests.carRequests.UpdateCarRequest;
import com.kodlamaio.rentACar.business.responses.carResponses.CarResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/cars")
public class CarsController {

	private CarService carService;

	public CarsController(CarService carService) {
		this.carService = carService;
	}

	@PostMapping("/add")
	public Result add(@RequestBody @Valid CreateCarRequest createCarRequest) {
		return carService.add(createCarRequest);
	}

	@GetMapping("/getall")
	public DataResult<List<CarResponse>> getAll() {
		return carService.getAll();
	}

	@DeleteMapping("/{id}")
	public Result delete(@PathVariable int id) {
		return carService.deleteById(id);
	}

	@PutMapping("/update")
	public Result update(@RequestBody UpdateCarRequest updateCarRequest) {
		return carService.update(updateCarRequest);
	}
}
