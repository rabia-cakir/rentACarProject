package com.kodlamaio.rentACar.api;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentACar.business.abstracts.BrandService;
import com.kodlamaio.rentACar.business.requests.brands.CreateBrandRequest;

@RestController
@RequestMapping("/api/brands")
public class BrandsController {
	
	private BrandService brandService;
	
	public BrandsController(BrandService brandService) {
		super();
		this.brandService = brandService;
	}

	@GetMapping("/getall")
	public List<CreateBrandRequest> getAll()
	{
		return brandService.getAll();
	}
	
	@PostMapping("/add")
	public void add(@RequestBody CreateBrandRequest createBrandRequest)
	{
		brandService.add(createBrandRequest);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable int id)
	{
		brandService.deleteById(id);
	}
	
	@PutMapping("/{id}")
	public void update(@PathVariable int id, @RequestBody CreateBrandRequest createBrandRequest)
	{
		brandService.update(createBrandRequest, id);
	}

}
