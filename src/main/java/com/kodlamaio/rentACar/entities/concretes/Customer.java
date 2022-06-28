package com.kodlamaio.rentACar.entities.concretes;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customers")
public class Customer extends User {

	@OneToMany(mappedBy = "customer")
	private List<Rental> rentals;

	@OneToMany(mappedBy = "customer")
	private List<Address> addresses;

}
