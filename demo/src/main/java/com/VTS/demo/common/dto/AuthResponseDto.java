package com.VTS.demo.common.dto;

public class AuthResponseDto {
	private String token;
	private String email;
	private String fullName;
	private String role;

	public AuthResponseDto(String token, String email, String fullName, String role) {
		this.token = token;
		this.email = email;
		this.fullName = fullName;
		this.role = role;
	}

	public String getToken() {
		return token;
	}

	public String getEmail() {
		return email;
	}

	public String getFullName() {
		return fullName;
	}

	public String getRole() {
		return role;
	}

}
