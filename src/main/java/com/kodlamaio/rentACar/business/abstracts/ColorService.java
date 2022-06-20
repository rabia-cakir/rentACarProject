package com.kodlamaio.rentACar.business.abstracts;

import java.util.List;

import com.kodlamaio.rentACar.business.requests.colors.CreateColorRequest;
import com.kodlamaio.rentACar.business.requests.colors.UpdateColorRequest;
import com.kodlamaio.rentACar.business.responses.colors.ColorResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;

public interface ColorService {
	Result add(CreateColorRequest createColorRequest);

	Result deleteById(int id);

	DataResult<List<ColorResponse>> getAll();

	Result update(UpdateColorRequest updateColorRequest);

	DataResult<ColorResponse> getColorById(int id);
}
