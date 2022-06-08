package com.kodlamaio.rentACar.business.abstracts;

import java.util.List;

import com.kodlamaio.rentACar.business.requests.brands.CreateBrandRequest;

//response request pattern
public interface BrandService {
	void add(CreateBrandRequest createBrandRequest);
	List<CreateBrandRequest> getAll();
	void deleteById(int id);
	void update(CreateBrandRequest createBrandRequest, int id);
	

	

}
