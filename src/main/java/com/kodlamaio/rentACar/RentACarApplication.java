package com.kodlamaio.rentACar;

import java.util.HashMap;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.kodlamaio.rentACar.core.utilities.exceptions.BusinessException;
import com.kodlamaio.rentACar.core.utilities.results.ErrorDataResult;

@SpringBootApplication
@RestControllerAdvice
public class RentACarApplication {

	public static void main(String[] args) {
		SpringApplication.run(RentACarApplication.class, args);
	}
	//spring bean anotasyonunu gördüğü zaman uygulama başlayınca bunu belleğe atar.
	@Bean
	public ModelMapper getModelMapper()
	{
		return new ModelMapper();
	}
	
	@ExceptionHandler
	@ResponseStatus(code=HttpStatus.BAD_REQUEST)//500 hatası dönüyor
	public ErrorDataResult<Object> handleBusinessException(BusinessException businessException)
	{
		ErrorDataResult<Object> errorDataResult=new ErrorDataResult<Object>(businessException.getMessage(),"BUSINESS.EXCEPTION");
		return errorDataResult;
	}
	
	@ExceptionHandler
	@ResponseStatus(code=HttpStatus.BAD_REQUEST)
	public ErrorDataResult<Object> handleValidationException(MethodArgumentNotValidException methodArgumentNotValidException)
	{
		//hangi alanda hata oldu, hata ne
		Map<String,String> validationErrors=new HashMap<String,String>();
		for(FieldError fieldError:methodArgumentNotValidException.getBindingResult().getFieldErrors())//hataları for each'le dönüyoruz
		{
			validationErrors.put(fieldError.getField(),fieldError.getDefaultMessage());//her hata map olarak tanımladığımız validationErrors' ekleniyor.
		}
		ErrorDataResult<Object> errorDataResult=new ErrorDataResult<Object>(validationErrors, "VALIDATION.EXCEPTION");//validation'ı errordata result'a çeviriyoruz.
		return errorDataResult;
	
		
		
		
	}
}
