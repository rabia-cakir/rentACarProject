package com.kodlamaio.rentACar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.rentACar.entities.concretes.AdditionalService;
import com.kodlamaio.rentACar.entities.concretes.Invoice;
import com.kodlamaio.rentACar.entities.concretes.Rental;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
	
	Invoice findByRentalId(int rentalId);
	
}
