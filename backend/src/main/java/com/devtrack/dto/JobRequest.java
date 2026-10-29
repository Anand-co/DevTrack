package com.devtrack.dto;
import com.devtrack.entity.JobStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
public record JobRequest(@NotBlank @Size(max=140) String company, @NotBlank @Size(max=140) String role,
 @Size(max=140) String location, @Size(max=500) String jobUrl, @NotNull JobStatus status, LocalDate appliedDate, @Size(max=3000) String notes) {}
