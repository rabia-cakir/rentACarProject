package com.kodlamaio.rentACar.core.utilities.mapping;

import org.modelmapper.ModelMapper;

public interface ModelMapperService {
	//mapping strategies
	//request olduğunda her şey maplenmek zorunda
	//response'da her şeyin maplenmesine gerek yok
	ModelMapper forResponse();
	ModelMapper forRequest();
}
