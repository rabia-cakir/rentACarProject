package com.kodlamaio.rentACar.api;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentACar.business.abstracts.MaintenanceService;
import com.kodlamaio.rentACar.business.requests.maintenance.CreateMaintenanceRequest;
import com.kodlamaio.rentACar.business.requests.maintenance.UpdateMaintenanceRequest;
import com.kodlamaio.rentACar.business.responses.maintenances.MaintenanceResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/maintenance")
public class MaintenancesController {

	MaintenanceService maintenanceService;

	public MaintenancesController(MaintenanceService maintenanceService) {
		this.maintenanceService = maintenanceService;
	}

	@GetMapping("/getall")
	public DataResult<List<MaintenanceResponse>> getAll() {
		return maintenanceService.getAll();
	}

	@PostMapping("/add")
	public Result add(@RequestBody CreateMaintenanceRequest createMaintenanceRequest, @RequestParam int carId) {

		return maintenanceService.add(createMaintenanceRequest, carId);

	}

	@DeleteMapping("/{id}")
	public Result delete(@PathVariable int id) {
		return maintenanceService.delete(id);
	}

	@PutMapping("/{id}")
	public Result update(@PathVariable int id, @RequestBody UpdateMaintenanceRequest updateMaintenanceRequest) {
		return maintenanceService.update(id, updateMaintenanceRequest);

	}
}
