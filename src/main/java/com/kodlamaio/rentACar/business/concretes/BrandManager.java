package com.kodlamaio.rentACar.business.concretes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.BrandService;
import com.kodlamaio.rentACar.business.requests.brandRequests.CreateBrandRequest;
import com.kodlamaio.rentACar.business.requests.brandRequests.UpdateBrandRequest;
import com.kodlamaio.rentACar.business.responses.brandResponses.BrandResponse;
import com.kodlamaio.rentACar.core.utilities.exceptions.BusinessException;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.BrandRepository;
import com.kodlamaio.rentACar.entities.concretes.Brand;

@Service
public class BrandManager implements BrandService {

	private BrandRepository brandRepository;
	private ModelMapperService modelMapperService;

	@Autowired
	public BrandManager(BrandRepository brandRepository, ModelMapperService modelMapperService) {
		this.brandRepository = brandRepository;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public DataResult<List<BrandResponse>> getAll() {
		List<Brand> brands = brandRepository.findAll();

		List<BrandResponse> response = brands.stream()
				.map(brand -> this.modelMapperService.forResponse().map(brand, BrandResponse.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<>(response, "DATA.LISTED.SUCCESSFULLY");

	}

	@Override
	public DataResult<BrandResponse> getBrandById(int id) {
		checkIfBrandExistById(id);
		Brand brand = this.brandRepository.findById(id).get();
		return new SuccessDataResult<>(modelMapperService.forResponse().map(brand, BrandResponse.class));
	}

	@Override
	public Result add(CreateBrandRequest createBrandRequest) {

		checkIfBrandExistByName(createBrandRequest.getName());
		Brand brand = this.modelMapperService.forRequest().map(createBrandRequest, Brand.class);
		this.brandRepository.save(brand);

		return new SuccessResult("BRAND.ADDED");

	}

	@Override
	public Result update(UpdateBrandRequest updateBrandRequest) {
		checkIfBrandExistById(updateBrandRequest.getId());
		checkIfBrandExistByName(updateBrandRequest.getName());
		Brand brand = this.modelMapperService.forRequest().map(updateBrandRequest, Brand.class);
		brandRepository.save(brand);
		return new SuccessResult("BRAND.UPDATED");

	}

	@Override
	public Result deleteById(int id) {
		checkIfBrandExistById(id);
		brandRepository.deleteById(id);
		return new SuccessResult("BRAND.DELETED");

	}

	private void checkIfBrandExistByName(String name) {
		Brand currentBrand = this.brandRepository.findByName(name);
		if (currentBrand != null)
			throw new BusinessException("BRAND.EXIST");
	}

	private void checkIfBrandExistById(int id) {
		Optional<Brand> brand = this.brandRepository.findById(id);
		if (brand.isEmpty())
			throw new BusinessException("BRAND.IS.NOT.EXIST");
	}

}
