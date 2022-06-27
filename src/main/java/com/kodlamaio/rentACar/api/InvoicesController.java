package com.kodlamaio.rentACar.api;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentACar.business.concretes.InvoiceManager;
import com.kodlamaio.rentACar.business.requests.invoiceRequests.CreateInvoiceRequest;
import com.kodlamaio.rentACar.business.responses.invoiceResponses.InvoiceAdditionalServiceResponse;
import com.kodlamaio.rentACar.business.responses.invoiceResponses.InvoiceRentalResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/invoice")
public class InvoicesController {

	private InvoiceManager invoiceRentalManager;

	public InvoicesController(InvoiceManager invoiceRentalManager) {
		super();
		this.invoiceRentalManager = invoiceRentalManager;
	}

	@GetMapping("/getRentalInvoice")
	public DataResult<List<InvoiceRentalResponse>> getRentalInvoice() {
		return invoiceRentalManager.getAllRentalInvoice();
	}

	@GetMapping("/getRentalAdditionalServiceInvoice")
	public DataResult<List<InvoiceAdditionalServiceResponse>> getAdditionalServiceInvoice() {
		return invoiceRentalManager.getAllAdditionalServiceInvoice();
	}

	
	@PostMapping("/add")
	public Result add(@RequestBody CreateInvoiceRequest createInvoiceRentalRequest) {
		return invoiceRentalManager.add(createInvoiceRentalRequest);
	}

	@DeleteMapping("/{id}")

	public Result delete(@PathVariable int id) {
		return invoiceRentalManager.delete(id);
	}

}
