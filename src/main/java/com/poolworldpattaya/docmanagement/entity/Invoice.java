package com.poolworldpattaya.docmanagement.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
@Entity
public class Invoice {
	@Id
	@GeneratedValue
	private UUID id;

	@OneToOne(mappedBy = "invoice")
	private Order order;

	@Column(nullable = false)
	private Date createdAt;

	@Column(nullable = true)
	private Date paidAt;

	@PrePersist
	protected void onCreate() {
		createdAt = new Date();
	}
}
