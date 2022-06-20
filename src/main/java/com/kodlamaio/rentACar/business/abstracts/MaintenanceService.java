package com.kodlamaio.rentACar.business.abstracts;

import java.util.List;

import com.kodlamaio.rentACar.business.requests.maintenance.CreateMaintenanceRequest;
import com.kodlamaio.rentACar.business.requests.maintenance.UpdateMaintenanceRequest;
import com.kodlamaio.rentACar.business.responses.maintenances.MaintenanceResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;

public interface MaintenanceService {
	DataResult<List<MaintenanceResponse>> getAll();

	Result add(CreateMaintenanceRequest createMaintenanceRequest, int carId);

	Result update(int id, UpdateMaintenanceRequest updateCreateMaintenance);

	Result delete(int id);

	DataResult<MaintenanceResponse> getById(int id);

}
