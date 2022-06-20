package com.kodlamaio.rentACar.business.concretes;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.MaintenanceService;
import com.kodlamaio.rentACar.business.requests.maintenance.CreateMaintenanceRequest;
import com.kodlamaio.rentACar.business.requests.maintenance.UpdateMaintenanceRequest;
import com.kodlamaio.rentACar.business.responses.maintenances.MaintenanceResponse;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.CarRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.MaintenanceRepository;
import com.kodlamaio.rentACar.entities.concretes.Car;
import com.kodlamaio.rentACar.entities.concretes.Maintenance;

@Service
public class MaintenanceManager implements MaintenanceService {

	private MaintenanceRepository maintenanceRepository;
	private CarRepository carRepository;
	private ModelMapperService modelMapperService;
	LocalDate currentDate = LocalDate.now();

	@Autowired
	public MaintenanceManager(MaintenanceRepository maintenanceRepository, CarRepository carRepository,
			ModelMapperService modelMapperService) {
		super();
		this.maintenanceRepository = maintenanceRepository;
		this.carRepository = carRepository;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public Result add(CreateMaintenanceRequest createMaintenanceRequest, int carId) {
		Maintenance maintenance = this.modelMapperService.forRequest().map(createMaintenanceRequest, Maintenance.class);
		maintenance.setDateSent(currentDate);
		Car car = carRepository.getById(createMaintenanceRequest.getCarId());
		car.setState(2);
		maintenance.setCar(car);
		maintenanceRepository.save(maintenance);
		return new SuccessResult("MAINTENANCE.ADDED");

	}

	@Override
	public Result update(int id, UpdateMaintenanceRequest updateMaintenanceRequest) {

		Optional<Maintenance> maintenance = maintenanceRepository.findById(id);
		if (maintenance.isPresent()) {
			Maintenance updateMaintenance = maintenance.get();
			Car car = carRepository.getById(updateMaintenanceRequest.getCarId());
			if (car.getState() == 2) {
				car.setState(1);
				updateMaintenance.setCar(car);
				maintenanceRepository.save(updateMaintenance);
			}
		}

		return new SuccessResult("MAINTENANCE.UPDATED");
	}

	@Override
	public DataResult<List<MaintenanceResponse>> getAll() {
		List<Maintenance> maintenances = maintenanceRepository.findAll();
		List<MaintenanceResponse> response = maintenances.stream()
				.map(maintenance -> modelMapperService.forResponse().map(maintenance, MaintenanceResponse.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<MaintenanceResponse>>(response, "DATA.LISTED.SUCCESSFULLY");
	}

	@Override
	public Result delete(int id) {
		maintenanceRepository.deleteById(id);
		return new SuccessResult("MAINTENANCE.DELETED");
	}

	@Override
	public DataResult<MaintenanceResponse> getById(int id) {
		Maintenance maintenance = maintenanceRepository.getById(id);
		return new SuccessDataResult<MaintenanceResponse>(
				modelMapperService.forResponse().map(maintenance, MaintenanceResponse.class));
	}

}
