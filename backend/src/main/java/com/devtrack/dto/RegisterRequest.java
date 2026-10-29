package com.devtrack.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
public record RegisterRequest(@NotBlank @Size(max=100) String fullName, @Email @NotBlank @Size(max=180) String email, @NotBlank @Size(min=8, max=72) String password) {}
