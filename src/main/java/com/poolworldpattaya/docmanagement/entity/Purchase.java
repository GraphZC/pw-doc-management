package com.poolworldpattaya.docmanagement.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Purchase {
	@Id
	@GeneratedValue
	private UUID id;

	@ManyToOne
	private Product product;

	@ManyToOne
	private Pool pool;

	@ManyToOne
	private CustomerOrder customerOrder;

//	@Column(nullable = true)
//	private UUID poolId;

//	@Column(nullable = true)
//	private UUID orderId;
	@Column(nullable = false)
	private double quantity;
}
