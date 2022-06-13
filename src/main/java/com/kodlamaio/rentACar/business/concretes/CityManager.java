package com.kodlamaio.rentACar.business.concretes;

import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.CityService;
import com.kodlamaio.rentACar.business.requests.cities.CreateCityRequest;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
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
	public void add(CreateCityRequest createCityRequest) {
		
		City city=this.modelMapperService.forRequest().map(createCityRequest, City.class);
		this.cityRepository.save(city);
	}



	@Override
	public City findById(int id) {
		return cityRepository.findById(id).get();
	}

}
