package com.poolworldpattaya.docmanagement.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Order {
	@Id
	@GeneratedValue
	private UUID id;

	@Column(nullable = false)
	private UUID purchaseId;

	@OneToMany(mappedBy = "order")
	private List<Purchase> purchase;

	@Column(nullable = false)
	private UUID customerId;

	@ManyToOne
	@JoinColumn(name = "customerId", nullable = false)
	private Customer customer;

	@Column(nullable = false)
	private UUID employeeId;

	@ManyToOne
	@JoinColumn(name = "employeeId", nullable = true)
	private Employee employee;

	@Column(nullable = true)
	private UUID invoiceId;

	@OneToOne
	@JoinColumn(name = "invoiceId", nullable = true)
	private Invoice invoice;

	@Column(nullable = false)
	private Date createdAt;

	@PrePersist
	protected void onCreate() {
		createdAt = new Date();
	}
}
