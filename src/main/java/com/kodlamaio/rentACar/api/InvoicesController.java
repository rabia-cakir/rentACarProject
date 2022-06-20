package com.kodlamaio.rentACar.api;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentACar.business.concretes.InvoiceRentalManager;
import com.kodlamaio.rentACar.business.requests.invoices.CreateInvoiceRentalRequest;
import com.kodlamaio.rentACar.business.responses.invoices.InvoiceAdditionalServiceResponse;
import com.kodlamaio.rentACar.business.responses.invoices.InvoiceRentalResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/invoice")
public class InvoicesController {

	private InvoiceRentalManager invoiceRentalManager;

	public InvoicesController(InvoiceRentalManager invoiceRentalManager) {
		super();
		this.invoiceRentalManager = invoiceRentalManager;
	}

	@GetMapping("/getallRentalInvoice")
	public DataResult<List<InvoiceRentalResponse>> getAllRentalInvoice() {
		return invoiceRentalManager.getAllRentalInvoice();
	}

	@GetMapping("/getallRentalAdditionalServiceInvoice")
	public DataResult<List<InvoiceAdditionalServiceResponse>> getAllAdditionalServiceInvoice() {
		return invoiceRentalManager.getAllAdditionalServiceInvoice();
	}

	
	@PostMapping("/add")
	public Result add(@RequestBody CreateInvoiceRentalRequest createInvoiceRentalRequest) {
		return invoiceRentalManager.add(createInvoiceRentalRequest);
	}

	@DeleteMapping("/{id}")

	public Result delete(@PathVariable int id) {
		return invoiceRentalManager.delete(id);
	}

}
