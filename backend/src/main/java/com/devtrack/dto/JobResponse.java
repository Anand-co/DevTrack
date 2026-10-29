package com.devtrack.dto;
import com.devtrack.entity.JobApplication;
import com.devtrack.entity.JobStatus;
import java.time.LocalDate;
import java.time.LocalDateTime;
public record JobResponse(Long id, String company, String role, String location, String jobUrl, JobStatus status, LocalDate appliedDate, String notes, LocalDateTime createdAt, LocalDateTime updatedAt) {
 public static JobResponse from(JobApplication j){return new JobResponse(j.getId(),j.getCompany(),j.getRole(),j.getLocation(),j.getJobUrl(),j.getStatus(),j.getAppliedDate(),j.getNotes(),j.getCreatedAt(),j.getUpdatedAt());}
}
