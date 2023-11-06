package com.poolworldpattaya.docmanagement.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Entity
public class CustomerOrder {
	@Id
	@GeneratedValue
	private UUID id;

//	@Column(nullable = false)
//	private UUID customerId;
//
//	@Column(nullable = false)
//	private UUID employeeId;
//
//	@Column(nullable = true)
//	private UUID invoiceId;
//
//	@Column(nullable = false)
//	private UUID purchaseId;

	@ManyToOne
	private Customer customer;

	@ManyToOne
	private Employee employee;

	@OneToOne
	private Invoice invoice;

	@Column(nullable = false)
	private Date createdAt;

	@PrePersist
	protected void onCreate() {
		createdAt = new Date();
	}
}
