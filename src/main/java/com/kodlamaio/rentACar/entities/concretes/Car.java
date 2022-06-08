package com.kodlamaio.rentACar.entities.concretes;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.kodlamaio.rentACar.business.requests.brands.CreateBrandRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="cars")
public class Car {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="description")
	private String description;
	@Column(name="dailyPrice")
	private double dailyPrice;
	
	@ManyToOne
	@JoinColumn(name="brands")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Brand brand;
	
	@ManyToOne
	@JoinColumn(name="colors")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Color color;
	
	

}
