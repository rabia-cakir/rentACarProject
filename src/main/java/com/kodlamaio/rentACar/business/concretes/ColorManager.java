package com.kodlamaio.rentACar.business.concretes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.ColorService;
import com.kodlamaio.rentACar.business.requests.colors.CreateColorRequest;
import com.kodlamaio.rentACar.dataAccess.abstracts.ColorRepository;
import com.kodlamaio.rentACar.entities.concretes.Color;

@Service
public class ColorManager implements ColorService {
	private ColorRepository colorRepository;

	@Autowired
	public ColorManager(ColorRepository colorRepository) {
		super();
		this.colorRepository = colorRepository;
	}

	@Override
	public void add(CreateColorRequest createColorRequest) {
		//mapping
		Color color=new Color();
		color.setName(createColorRequest.getName());
		this.colorRepository.save(color);
		
	}

	@Override
	public void deleteById(int id) {
		colorRepository.deleteById(id);
	}

	@Override
	public List<CreateColorRequest> getAll() {
		List<Color> colors=colorRepository.findAll();
		List<CreateColorRequest> colorRequests=new ArrayList<>();
		CreateColorRequest colorRequest=new CreateColorRequest();
		
		for(Color color:colors)
		{
			colorRequest.setName(color.getName());
			colorRequests.add(colorRequest);
		}
		
		return colorRequests;
		
	}

	@Override
	public void update(CreateColorRequest createColorRequest, int id) {
		//mapping
		Color color=new Color();
		color.setName(createColorRequest.getName());
		Optional<Color> currentColor=colorRepository.findById(id);
		if(currentColor.isPresent())
		{
			Color foundColor=currentColor.get();
			foundColor.setName(color.getName());
			colorRepository.save(foundColor);
			
		}
		
	}
	
	

}
