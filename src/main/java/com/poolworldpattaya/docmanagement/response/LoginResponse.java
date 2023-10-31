package com.poolworldpattaya.docmanagement.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
	private UUID id;
	private String username;
	private String name;
	private String roles;
	private String accessToken;
	private String refreshToken;
	private Date accessTokenExpires;
	private Date refreshTokenExpires;
}
