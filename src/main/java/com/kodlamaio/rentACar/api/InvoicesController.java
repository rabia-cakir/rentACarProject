package com.kodlamaio.rentACar.api;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentACar.business.abstracts.InvoiceService;
import com.kodlamaio.rentACar.business.concretes.InvoiceManager;
import com.kodlamaio.rentACar.business.requests.invoiceRequests.CreateInvoiceRequest;
import com.kodlamaio.rentACar.business.responses.invoiceResponses.InvoiceAdditionalServiceResponse;
import com.kodlamaio.rentACar.business.responses.invoiceResponses.InvoiceCorporateCustomerResponse;
import com.kodlamaio.rentACar.business.responses.invoiceResponses.InvoiceIndividualCustomerResponse;
import com.kodlamaio.rentACar.business.responses.invoiceResponses.InvoiceRentalResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/invoice")
public class InvoicesController {

	private InvoiceService invoiceService;

	

	public InvoicesController(InvoiceService invoiceService) {
		super();
		this.invoiceService = invoiceService;
	}

	/*@GetMapping("/getRentalInvoice")
	public DataResult<List<InvoiceRentalResponse>> getRentalInvoice() {
		return invoiceService.getAllRentalInvoice();
	*/

/*	@GetMapping("/getRentalAdditionalServiceInvoice")
	public DataResult<List<InvoiceAdditionalServiceResponse>> getAdditionalServiceInvoice() {
		return invoiceService.getAllAdditionalServiceInvoice();
	}
	*/
	@GetMapping("getAllindividualCustomerInvoice")
	public DataResult<List<InvoiceIndividualCustomerResponse>> getAllindividualCustomerInvoice(){
		return this.invoiceService.getAllindividualCustomerInvoice();
	}
	
	@GetMapping("getInvoiceCorporateCustomer")
	
	public DataResult<List<InvoiceCorporateCustomerResponse>> getAllcorporateCustomerInvoice(){
		return this.invoiceService.getAllcorporateCustomerInvoice();
	}
	

	
	@PostMapping("/add")
	public Result add(@RequestBody CreateInvoiceRequest createInvoiceRentalRequest) {
		return invoiceService.add(createInvoiceRentalRequest);
	}

/*	@DeleteMapping("/{id}")

	public Result delete(@PathVariable int id) {
		return invoiceService.delete(id);
	}
*/
}
