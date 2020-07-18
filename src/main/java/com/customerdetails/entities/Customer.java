package com.customerdetails.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "CUSTOMER")
@NoArgsConstructor
@Getter
@Setter
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Column(name = "first_name", nullable = false, length = 20)
	@Size(max = 20)
	@NotBlank(message = "First Name is mandatory")
	private String firstName;

	@Column(name = "last_name", nullable = false, length = 20)
	@Size(max = 20)
	@NotBlank(message = "Last Name is mandatory")
	private String lastName;

	@Column(name = "age", nullable = false)
	@Min(1)
	@Max(150)
	private int age;

	@JsonManagedReference
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
	@NotNull
	private Set<Address> addresses;

	public void addAddress(Address address) {
		addresses.add(address);
		address.setCustomer(this);
	}

	public void removeAddress(Address address) {
		addresses.remove(address);
		address.setCustomer(null);
	}

	public Customer(@Size(max = 20) @NotBlank(message = "First Name is mandatory") String firstName,
			@Size(max = 20) @NotBlank(message = "Last Name is mandatory") String lastName, @Min(1) @Max(150) int age,
			Set<Address> addresses) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.addresses = addresses;
	}

}
