package com.poolworldpattaya.docmanagement.request;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditCustomerRequest {
	@Column(nullable = false)
	private String name;

	@Column(nullable = true)
	private String address;

	@Column(nullable = true)
	private String telephone;

	@Column(nullable = true)
	private String taxId;
}
