package com.kodlamaio.rentACar.business.concretes;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.CarService;
import com.kodlamaio.rentACar.business.abstracts.MaintenanceService;
import com.kodlamaio.rentACar.business.requests.maintenanceRequests.CreateMaintenanceRequest;
import com.kodlamaio.rentACar.business.requests.maintenanceRequests.UpdateMaintenanceRequest;
import com.kodlamaio.rentACar.business.responses.maintenanceResponses.MaintenanceResponse;
import com.kodlamaio.rentACar.core.utilities.exceptions.BusinessException;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.MaintenanceRepository;
import com.kodlamaio.rentACar.entities.concretes.Maintenance;

@Service
public class MaintenanceManager implements MaintenanceService {

	private MaintenanceRepository maintenanceRepository;
	private ModelMapperService modelMapperService;
	private CarService carService;

	public MaintenanceManager(MaintenanceRepository maintenanceRepository, ModelMapperService modelMapperService,
			CarService carService) {
		super();
		this.maintenanceRepository = maintenanceRepository;
		this.modelMapperService = modelMapperService;
		this.carService = carService;
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
	public DataResult<MaintenanceResponse> getById(int id) {
		checkIfMaintenanceExistById(id);
		Maintenance maintenance = maintenanceRepository.findById(id).get();
		return new SuccessDataResult<MaintenanceResponse>(
				modelMapperService.forResponse().map(maintenance, MaintenanceResponse.class));
	}

	@Override
	public Result add(CreateMaintenanceRequest createMaintenanceRequest) {
		checkIfCarAlreadyMaintenance(createMaintenanceRequest.getCarId());
		checkIfCarRented(createMaintenanceRequest.getCarId());
		checkDates(createMaintenanceRequest.getDateSent(), createMaintenanceRequest.getDateReturned());
		Maintenance maintenance = this.modelMapperService.forRequest().map(createMaintenanceRequest, Maintenance.class);
		setStateAsMaintenance(createMaintenanceRequest.getCarId());
		maintenanceRepository.save(maintenance);
		return new SuccessResult("MAINTENANCE.ADDED");

	}

	@Override
	public Result update(UpdateMaintenanceRequest updateMaintenanceRequest) {
		checkIfMaintenanceExistById(updateMaintenanceRequest.getId());
		checkIfCarAlreadyMaintenance(updateMaintenanceRequest.getCarId());
		checkIfCarRented(updateMaintenanceRequest.getCarId());
		checkDates(updateMaintenanceRequest.getDateSent(), updateMaintenanceRequest.getDateReturned());
		return new SuccessResult("MAINTENANCE.UPDATED");
	}

	@Override
	public Result delete(int id) {
		checkIfMaintenanceExistById(id);
		maintenanceRepository.deleteById(id);
		return new SuccessResult("MAINTENANCE.DELETED");
	}

	private void checkIfCarExist(int carId) {
		if (!carService.getById(carId).isSuccess())
			throw new BusinessException("CAR.IS.NOT.EXIST");

	}

	private int checkState(int carId) {
		checkIfCarExist(carId);
		return carService.getCarById(carId).getState();

	}

	private void setStateAsMaintenance(int carId) {
		checkIfCarExist(carId);
		carService.getCarById(carId).setState(2);
	}

	private void checkIfCarAlreadyMaintenance(int carId) {
		int state = checkState(carId);
		if (state == 2)
			throw new BusinessException("CAR.IS.ALREADY.MAINTENANCE");

	}

	private void checkIfCarRented(int carId) {
		int state = checkState(carId);
		if (state == 3)
			throw new BusinessException("CAR.IS.RENTED");
	}

	private void checkDates(LocalDate pickUpDate, LocalDate returnDate) {
		if (!returnDate.isAfter(pickUpDate)) {
			throw new BusinessException("RETURN.DATE.SHOULD.BE.AFTER.THE.PICK.UP.DATE");
		}
	}

	private void checkIfMaintenanceExistById(int id) {
		Optional<Maintenance> maintenance = maintenanceRepository.findById(id);
		if (maintenance.isEmpty())
			throw new BusinessException("MAINTENANCE.IS.NOT.EXIST");
	}

}
