package com.kodlamaio.rentACar.business.concretes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.CityService;
import com.kodlamaio.rentACar.business.requests.cityRequests.CreateCityRequest;
import com.kodlamaio.rentACar.business.requests.cityRequests.UpdateCityRequest;
import com.kodlamaio.rentACar.business.responses.cityResponses.CityResponse;
import com.kodlamaio.rentACar.core.utilities.exceptions.BusinessException;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.CityRepository;
import com.kodlamaio.rentACar.entities.concretes.City;

@Service
public class CityManager implements CityService {

	private CityRepository cityRepository;
	private ModelMapperService modelMapperService;

	public CityManager(CityRepository cityRepository, ModelMapperService modelMapperService) {
		super();
		this.cityRepository = cityRepository;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public DataResult<List<CityResponse>> getAll() {
		List<City> cities = cityRepository.findAll();
		List<CityResponse> response = cities.stream()
				.map(city -> modelMapperService.forRequest().map(city, CityResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<CityResponse>>(response, "DATA.LISTED.SUCCESSFULLY");
	}

	@Override
	public DataResult<CityResponse> findById(int id) {
		checkIfCityExistById(id);
		City city = cityRepository.findById(id).get();
		return new SuccessDataResult<>(modelMapperService.forResponse().map(city, CityResponse.class));
	}

	@Override
	public Result add(CreateCityRequest createCityRequest) {
		checkIfCityExistByName(createCityRequest.getName());
		City city = this.modelMapperService.forRequest().map(createCityRequest, City.class);
		this.cityRepository.save(city);
		return new SuccessResult("CITY.ADDED");
	}

	@Override
	public Result update(UpdateCityRequest updateCityRequest) {
		checkIfCityExistById(updateCityRequest.getId());
		checkIfCityExistByName(updateCityRequest.getName());
		City city = this.modelMapperService.forRequest().map(updateCityRequest, City.class);
		this.cityRepository.save(city);
		return new SuccessResult("CITY.UPDATED");
	}

	@Override
	public Result delete(int id) {
		checkIfCityExistById(id);
		cityRepository.deleteById(id);
		return new SuccessResult("CITY.DELETED");
	}

	private void checkIfCityExistByName(String name) {
		City city = cityRepository.findByName(name);
		if (city != null)

			throw new BusinessException("CITY.EXIST");

	}

	private void checkIfCityExistById(int id) {
		Optional<City> city = cityRepository.findById(id);
		if (city.isEmpty())

			throw new BusinessException("CITY.IS.NOT.EXIST");

	}
}
