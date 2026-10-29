package com.devtrack.dto;
public record AuthResponse(String token, Long id, String fullName, String email) {}
