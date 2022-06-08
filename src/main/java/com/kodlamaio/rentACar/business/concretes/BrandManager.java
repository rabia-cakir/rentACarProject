package com.kodlamaio.rentACar.business.concretes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.BrandService;
import com.kodlamaio.rentACar.business.requests.brands.CreateBrandRequest;
import com.kodlamaio.rentACar.dataAccess.abstracts.BrandRepository;
import com.kodlamaio.rentACar.entities.concretes.Brand;


@Service
public class BrandManager implements BrandService{
	
	private BrandRepository brandRepository;
	
	
	@Autowired
	public BrandManager(BrandRepository brandRepository)
	{
		this.brandRepository=brandRepository;
	}


	@Override
	public void add(CreateBrandRequest createBrandRequest) {
		
		//mapping
		Brand brand=new Brand();
		brand.setName(createBrandRequest.getName());
		this.brandRepository.save(brand);
		
		
	}


	@Override
	public List<CreateBrandRequest> getAll() {
		//mapping
		List<Brand> brands=brandRepository.findAll();
		List<CreateBrandRequest> brandRequests=new ArrayList<>();
		CreateBrandRequest brandRequest=new CreateBrandRequest();
		for(Brand brand:brands)
		{
			
			brandRequest.setName(brand.getName());
			brandRequests.add(brandRequest);
			
		}
		
		return brandRequests;
	}


	@Override
	public void deleteById(int id) {
		
		brandRepository.deleteById(id);
		
		
	}


	@Override
	public void update(CreateBrandRequest createBrandRequest, int id) {
		//mapping
		Brand brand=new Brand();
		brand.setName(createBrandRequest.getName());
		Optional<Brand> currentBrand=brandRepository.findById(id);
		if(currentBrand.isPresent())
		{
			Brand foundBrand=currentBrand.get();
			foundBrand.setName(brand.getName());
			brandRepository.save(foundBrand);
			
		}
		
		
	}


	

	

}
