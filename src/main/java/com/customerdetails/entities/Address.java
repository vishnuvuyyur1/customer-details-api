package com.customerdetails.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Entity
@Table(name = "ADDRESS")
@Data
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Column(name = "address", nullable = false, length = 50)
	@Size(max = 50)
	@NotBlank(message = "Address is mandatory")
	private String address;

	@Column(name = "city", nullable = false, length = 20)
	@Size(max = 20)
	@NotBlank(message = "city is mandatory")
	private String city;

	@Column(name = "state", nullable = false, length = 20)
	@Size(max = 20)
	@NotBlank(message = "State/Province is mandatory")
	private String state;

	@Column(name = "zip_code", nullable = false, length = 10)
	@Size(max = 10)
	@NotBlank(message = "Zip Code is mandatory")
	private String zipCode;

	@Column(name = "country", nullable = false, length = 20)
	@Size(max = 20)
	@NotBlank(message = "Country is mandatory")
	private String country;
	
	@Column(name = "active", nullable = false)
	@Size(max = 20)
	@NotBlank(message = "Current living address indicator is mandatory")
	private boolean active;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	private Customer customer;
}
