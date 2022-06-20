package com.kodlamaio.rentACar.business.concretes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.ColorService;
import com.kodlamaio.rentACar.business.requests.colors.CreateColorRequest;
import com.kodlamaio.rentACar.business.requests.colors.UpdateColorRequest;
import com.kodlamaio.rentACar.business.responses.colors.ColorResponse;
import com.kodlamaio.rentACar.core.utilities.exceptions.BusinessException;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.ColorRepository;
import com.kodlamaio.rentACar.entities.concretes.Color;

@Service
public class ColorManager implements ColorService {
	private ColorRepository colorRepository;
	private ModelMapperService modelMapperService;

	@Autowired
	public ColorManager(ColorRepository colorRepository, ModelMapperService modelMapperService) {
		super();
		this.colorRepository = colorRepository;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public Result add(CreateColorRequest createColorRequest) {

		checkIfColorExistByName(createColorRequest.getName());
		Color color = this.modelMapperService.forRequest().map(createColorRequest, Color.class);
		this.colorRepository.save(color);
		return new SuccessResult("COLOR.ADDED");

	}

	@Override
	public Result deleteById(int id) {
		checkIfColorExistById(id);
		colorRepository.deleteById(id);
		return new SuccessResult("COLOR.DELETED");
	}

	@Override
	public DataResult<List<ColorResponse>> getAll() {
		List<Color> colors = colorRepository.findAll();
		List<ColorResponse> response = colors.stream()
				.map(color -> this.modelMapperService.forResponse().map(color, ColorResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<>(response, "DATA.LISTED.SUCCESSFULLY");

	}

	@Override
	public Result update(UpdateColorRequest updateColorRequest) {
		checkIfColorExistByName(updateColorRequest.getName());
		Color color = modelMapperService.forRequest().map(updateColorRequest, Color.class);
		colorRepository.save(color);
		return new SuccessResult("COLOR.UPDATED");
	}

	@SuppressWarnings("deprecation")
	@Override
	public DataResult<ColorResponse> getColorById(int id) {
		checkIfColorExistById(id);
		Color color = colorRepository.getById(id);
		return new SuccessDataResult<>(modelMapperService.forResponse().map(color, ColorResponse.class));
	}

	private void checkIfColorExistByName(String name) {
		Color color = colorRepository.findByName(name);
		if (color != null)
			throw new BusinessException("COLOR.EXIST");
	}

	private void checkIfColorExistById(int id) {
		Optional<Color> color = colorRepository.findById(id);
		if (color.isEmpty())
			throw new BusinessException("COLOR.IS.NOT.EXIST");
	}

}
