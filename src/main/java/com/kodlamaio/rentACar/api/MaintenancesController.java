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

import com.kodlamaio.rentACar.business.abstracts.MaintenanceService;
import com.kodlamaio.rentACar.business.requests.maintenanceRequests.CreateMaintenanceRequest;
import com.kodlamaio.rentACar.business.requests.maintenanceRequests.UpdateMaintenanceRequest;
import com.kodlamaio.rentACar.business.responses.maintenanceResponses.MaintenanceResponse;
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
	public Result add(@RequestBody CreateMaintenanceRequest createMaintenanceRequest) {

		return maintenanceService.add(createMaintenanceRequest);

	}

	@DeleteMapping("/{id}")
	public Result delete(@PathVariable int id) {
		return maintenanceService.delete(id);
	}

	@PutMapping("/update")
	public Result update(@RequestBody UpdateMaintenanceRequest updateMaintenanceRequest) {
		return maintenanceService.update(updateMaintenanceRequest);

	}
}
