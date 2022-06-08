package com.kodlamaio.rentACar.business.abstracts;

import java.util.List;

import com.kodlamaio.rentACar.business.requests.colors.CreateColorRequest;

public interface ColorService {
	void add(CreateColorRequest createColorRequest);
	void deleteById(int id);
	List<CreateColorRequest> getAll();
	void update(CreateColorRequest createColorRequest, int id);

}
