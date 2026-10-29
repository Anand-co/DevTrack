package com.devtrack.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "app_users", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class AppUser {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(nullable = false, length = 100) private String fullName;
    @Column(nullable = false, unique = true, length = 180) private String email;
    @Column(nullable = false) private String password;
    @Column(nullable = false) private LocalDateTime createdAt = LocalDateTime.now();
    public AppUser() {}
    public AppUser(String fullName, String email, String password) { this.fullName=fullName; this.email=email; this.password=password; }
    public Long getId(){return id;} public String getFullName(){return fullName;} public void setFullName(String v){fullName=v;}
    public String getEmail(){return email;} public void setEmail(String v){email=v;} public String getPassword(){return password;} public void setPassword(String v){password=v;}
    public LocalDateTime getCreatedAt(){return createdAt;}
}
