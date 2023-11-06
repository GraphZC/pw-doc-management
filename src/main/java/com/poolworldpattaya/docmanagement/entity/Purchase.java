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

	@Column(nullable = true)
	private UUID productId;

	@ManyToOne
	@JoinColumn(name = "productId", nullable = true)
	private Product product;

	@Column(nullable = true)
	private UUID poolId;

	@ManyToOne
	@JoinColumn(name = "poolId", nullable = true)
	private Pool pool;

	@Column(nullable = true)
	private UUID orderId;

	@ManyToOne
	@JoinColumn(name = "orderId", nullable = true)
	private Order order;
}
