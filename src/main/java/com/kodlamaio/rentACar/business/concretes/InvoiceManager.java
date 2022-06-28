package com.kodlamaio.rentACar.business.concretes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.InvoiceService;
import com.kodlamaio.rentACar.business.requests.invoiceRequests.CreateInvoiceRequest;
import com.kodlamaio.rentACar.business.responses.invoiceResponses.InvoiceAdditionalServiceResponse;
import com.kodlamaio.rentACar.business.responses.invoiceResponses.InvoiceCorporateCustomerResponse;
import com.kodlamaio.rentACar.business.responses.invoiceResponses.InvoiceIndividualCustomerResponse;
import com.kodlamaio.rentACar.business.responses.invoiceResponses.InvoiceRentalResponse;
import com.kodlamaio.rentACar.core.utilities.exceptions.BusinessException;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.InvoiceRepository;
import com.kodlamaio.rentACar.entities.concretes.Invoice;

@Service
public class InvoiceManager implements InvoiceService{
	private InvoiceRepository invoiceRepository;
	private ModelMapperService modelMapperService;

	public InvoiceManager(InvoiceRepository invoiceRepository, ModelMapperService modelMapperService) {
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

	public Result add(CreateInvoiceRequest createInvoiceRentalRequest) {
		checkIfInvoiceExistByRentalId(createInvoiceRentalRequest.getRentalId());
		Invoice invoice = modelMapperService.forRequest().map(createInvoiceRentalRequest, Invoice.class);
		invoiceRepository.save(invoice);
		return new SuccessResult("INVOICE.ADDED");
	}

	public Result delete(int id) {
		checkIfInvoiceExistById(id);
		invoiceRepository.deleteById(id);
		return new SuccessResult("INVOICE.DELETED");
	}

	private void checkIfInvoiceExistById(int id) {
		Optional<Invoice> invoice = invoiceRepository.findById(id);
		if (invoice.isEmpty())
			throw new BusinessException("INVOICE.IS.NOT.EXIST");
	}

	private void checkIfInvoiceExistByRentalId(int rentalId) {
		Invoice invoice = invoiceRepository.findByRentalId(rentalId);
		if (invoice != null)
			throw new BusinessException("INVOICE.IS.ALREADY.EXIST");
	}

	

	
	@Override
	public DataResult<List<InvoiceCorporateCustomerResponse>> getAllcorporateCustomerInvoice() {
		// TODO Auto-generated method stub
		List<Invoice> invoiceCorporateCustomers=invoiceRepository.findAll();
		List<InvoiceCorporateCustomerResponse> response=invoiceCorporateCustomers.stream().map(
				invoiceCorporateCustomer -> modelMapperService.forResponse().map(invoiceCorporateCustomer, InvoiceCorporateCustomerResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<InvoiceCorporateCustomerResponse>>(response,"CORPORATE.CUSTOMER.LISTED.SUCCESSFULLY");
	}

	@Override
	public DataResult<List<InvoiceIndividualCustomerResponse>> getAllindividualCustomerInvoice() {
		// TODO Auto-generated method stub
		List<Invoice> invoiceIndividualCustomers=invoiceRepository.findAll();
		List<InvoiceIndividualCustomerResponse> response=invoiceIndividualCustomers.stream().map(
				invoiceIndividualCustomer -> modelMapperService.forResponse().map(invoiceIndividualCustomer, InvoiceIndividualCustomerResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<InvoiceIndividualCustomerResponse>>(response,"INDIVIDUAL.CUSTOMER.LISTED.SUCCESSFULLY");
	}

}
