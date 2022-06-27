package com.kodlamaio.rentACar.business.abstracts;

import java.util.List;

import com.kodlamaio.rentACar.business.requests.maintenanceRequests.CreateMaintenanceRequest;
import com.kodlamaio.rentACar.business.requests.maintenanceRequests.UpdateMaintenanceRequest;
import com.kodlamaio.rentACar.business.responses.maintenanceResponses.MaintenanceResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;

public interface MaintenanceService {
	DataResult<List<MaintenanceResponse>> getAll();

	Result add(CreateMaintenanceRequest createMaintenanceRequest);

	Result update(UpdateMaintenanceRequest updateCreateMaintenance);

	Result delete(int id);

	DataResult<MaintenanceResponse> getById(int id);

}
