package com.kodlamaio.rentACar.core.utilities.mapping;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;


@Service
public class ModelMapperServiceImpl implements ModelMapperService{

	
	private ModelMapper modelMapper;
	
	//modelmapper class olmasına rağmen new'leyip dependency injection yaptık
	//çünkü modelmapper her map işlemi için new'lendiğinde bellekte boş yer kaplar.
	//bunun yerine dependency injection ile bir instance oluşturup her seferinde onu kullanıyoruz
	public ModelMapperServiceImpl(ModelMapper modelMapper) {
		super();
		this.modelMapper = modelMapper;
	}

	@Override
	public ModelMapper forResponse() {
		this.modelMapper.getConfiguration()
		.setAmbiguityIgnored(true).setMatchingStrategy(MatchingStrategies.LOOSE);//loose'da her şey map'lenmek zorunda değil.
		return this.modelMapper;
	}

	@Override
	public ModelMapper forRequest() {
	
		this.modelMapper.getConfiguration()
		.setAmbiguityIgnored(true).setMatchingStrategy(MatchingStrategies.STANDARD);
		return this.modelMapper;
	}
	}


