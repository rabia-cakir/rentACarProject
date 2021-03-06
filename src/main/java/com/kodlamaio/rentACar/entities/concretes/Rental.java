package com.kodlamaio.rentACar.entities.concretes;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "rentals")
public class Rental {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name="total_days")
	private int totalDays;
	
	@Column(name="total_price")
	private double totalPrice;
	
	@Column(name = "pick_up_date")
	private LocalDate pickUpDate;
	
	@Column(name = "return_date")
	private LocalDate returnDate;
	
	@ManyToOne
	@JoinColumn(name="car_id")
	private Car car;
	
	@ManyToOne
	@JoinColumn(name="pickup_city_id")
	private City pickUpCity;
	
	@ManyToOne
	@JoinColumn(name="return_city_id")
	private City returnCity;
	
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customer;
	
	@OneToMany(mappedBy = "rental")
	private List<AdditionalService> additionalServices;

	@OneToOne(mappedBy = "rental")	
	private Invoice invoice;
	

	
}
