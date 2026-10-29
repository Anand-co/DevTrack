package com.devtrack.service;
import com.devtrack.dto.*;
import com.devtrack.entity.AppUser;
import com.devtrack.repository.UserRepository;
import com.devtrack.security.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
@Service
public class AuthService {
 private final UserRepository users; private final PasswordEncoder encoder; private final JwtService jwt;
 public AuthService(UserRepository users, PasswordEncoder encoder, JwtService jwt){this.users=users;this.encoder=encoder;this.jwt=jwt;}
 public AuthResponse register(RegisterRequest r){
  String email=r.email().trim().toLowerCase();
  if(users.existsByEmailIgnoreCase(email)) throw new ResponseStatusException(HttpStatus.CONFLICT,"An account with this email already exists");
  AppUser user=users.save(new AppUser(r.fullName().trim(),email,encoder.encode(r.password())));
  return new AuthResponse(jwt.generateToken(org.springframework.security.core.userdetails.User.withUsername(user.getEmail()).password(user.getPassword()).authorities("ROLE_USER").build()),user.getId(),user.getFullName(),user.getEmail());
 }
 public AuthResponse login(AuthRequest r){
  AppUser user=users.findByEmailIgnoreCase(r.email().trim()).orElseThrow(()->new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Invalid email or password"));
  if(!encoder.matches(r.password(),user.getPassword())) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Invalid email or password");
  var principal=org.springframework.security.core.userdetails.User.withUsername(user.getEmail()).password(user.getPassword()).authorities("ROLE_USER").build();
  return new AuthResponse(jwt.generateToken(principal),user.getId(),user.getFullName(),user.getEmail());
 }
}
