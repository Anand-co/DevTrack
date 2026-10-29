package com.devtrack.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "job_applications")
public class JobApplication {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(nullable=false, length=140) private String company;
    @Column(nullable=false, length=140) private String role;
    @Column(length=140) private String location;
    @Column(length=500) private String jobUrl;
    @Enumerated(EnumType.STRING) @Column(nullable=false, length=20) private JobStatus status = JobStatus.APPLIED;
    private LocalDate appliedDate = LocalDate.now();
    @Column(length=3000) private String notes;
    @ManyToOne(fetch=FetchType.LAZY, optional=false) @JoinColumn(name="user_id", nullable=false) private AppUser user;
    @Column(nullable=false) private LocalDateTime createdAt = LocalDateTime.now();
    @Column(nullable=false) private LocalDateTime updatedAt = LocalDateTime.now();
    @PreUpdate void onUpdate(){updatedAt=LocalDateTime.now();}
    public JobApplication() {}
    public Long getId(){return id;} public String getCompany(){return company;} public void setCompany(String v){company=v;}
    public String getRole(){return role;} public void setRole(String v){role=v;} public String getLocation(){return location;} public void setLocation(String v){location=v;}
    public String getJobUrl(){return jobUrl;} public void setJobUrl(String v){jobUrl=v;} public JobStatus getStatus(){return status;} public void setStatus(JobStatus v){status=v;}
    public LocalDate getAppliedDate(){return appliedDate;} public void setAppliedDate(LocalDate v){appliedDate=v;} public String getNotes(){return notes;} public void setNotes(String v){notes=v;}
    public AppUser getUser(){return user;} public void setUser(AppUser v){user=v;} public LocalDateTime getCreatedAt(){return createdAt;} public LocalDateTime getUpdatedAt(){return updatedAt;}
}
