package com.kodlamaio.rentACar.business.abstracts;

import java.util.List;

import com.kodlamaio.rentACar.business.requests.invoiceRequests.CreateInvoiceRequest;
import com.kodlamaio.rentACar.business.responses.invoiceResponses.InvoiceCorporateCustomerResponse;
import com.kodlamaio.rentACar.business.responses.invoiceResponses.InvoiceIndividualCustomerResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;

public interface InvoiceService {

	DataResult<List<InvoiceCorporateCustomerResponse>> getAllcorporateCustomerInvoice();
	DataResult<List<InvoiceIndividualCustomerResponse>> getAllindividualCustomerInvoice();
	Result add(CreateInvoiceRequest createInvoiceRequest);
}
