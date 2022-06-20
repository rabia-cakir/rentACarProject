package com.kodlamaio.rentACar.business.abstracts;

import java.util.List;

import com.kodlamaio.rentACar.business.requests.brands.CreateBrandRequest;
import com.kodlamaio.rentACar.business.requests.brands.UpdateBrandRequest;
import com.kodlamaio.rentACar.business.responses.brands.BrandResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;


public interface BrandService {
	Result add(CreateBrandRequest createBrandRequest);

	DataResult<List<BrandResponse>> getAll();

	Result deleteById(int id);

	Result update(UpdateBrandRequest updateBrandRequest);

	DataResult<BrandResponse> getBrandById(int id);

}
