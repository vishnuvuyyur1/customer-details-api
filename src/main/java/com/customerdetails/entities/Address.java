package com.customerdetails.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.customerdetails.model.AddressType;
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ADDRESS")
@NoArgsConstructor
@Getter
@Setter
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
	
    @Enumerated(EnumType.STRING)
    @Column(name="address_type",nullable=false, length=10)
    private AddressType addressType;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	private Customer customer;

	public Address(@Size(max = 50) @NotBlank(message = "Address is mandatory") String address,
			@Size(max = 20) @NotBlank(message = "city is mandatory") String city,
			@Size(max = 20) @NotBlank(message = "State/Province is mandatory") String state,
			@Size(max = 10) @NotBlank(message = "Zip Code is mandatory") String zipCode,
			@Size(max = 20) @NotBlank(message = "Country is mandatory") String country,
			@NotBlank(message = "Address Type is mandatory") AddressType addressType) {
		this.address = address;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.country = country;
		this.addressType = addressType;
	}

}
