package com.kodlamaio.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.requests.invoices.CreateInvoiceRentalRequest;
import com.kodlamaio.rentACar.business.responses.invoices.InvoiceAdditionalServiceResponse;
import com.kodlamaio.rentACar.business.responses.invoices.InvoiceRentalResponse;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.InvoiceRepository;
import com.kodlamaio.rentACar.entities.concretes.Invoice;

@Service
public class InvoiceRentalManager {
	private InvoiceRepository invoiceRepository;
	private ModelMapperService modelMapperService;

	public InvoiceRentalManager(InvoiceRepository invoiceRepository, ModelMapperService modelMapperService) {
		super();
		this.invoiceRepository = invoiceRepository;
		this.modelMapperService = modelMapperService;
	}

	public DataResult<List<InvoiceRentalResponse>> getAllRentalInvoice() {

		List<Invoice> invoices = invoiceRepository.findAll();
		List<InvoiceRentalResponse> response = invoices.stream()
				.map(invoice -> modelMapperService.forResponse().map(invoice, InvoiceRentalResponse.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<>(response, "DATA.LISTED.SUCCESSFULLLY");

	}
	
	public DataResult<List<InvoiceAdditionalServiceResponse>> getAllAdditionalServiceInvoice() {

		List<Invoice> invoices = invoiceRepository.findAll();
		List<InvoiceAdditionalServiceResponse> response = invoices.stream()
				.map(invoice -> modelMapperService.forResponse().map(invoice, InvoiceAdditionalServiceResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<>(response, "DATA.LISTED.SUCCESSFULLLY");

	}
	
	
	
	public Result add(CreateInvoiceRentalRequest createInvoiceRentalRequest)
	{
		Invoice invoice=modelMapperService.forRequest().map(createInvoiceRentalRequest, Invoice.class);
		invoiceRepository.save(invoice);
		return new SuccessResult("INVOICE.ADDED");
	}
	
	public Result delete(int id)
	{
		invoiceRepository.deleteById(id);
		return new SuccessResult("INVOICE.DELETED");
	}
	
	

}
