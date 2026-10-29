package com.devtrack.service;
import com.devtrack.dto.JobRequest;
import com.devtrack.dto.JobResponse;
import com.devtrack.entity.AppUser;
import com.devtrack.entity.JobApplication;
import com.devtrack.entity.JobStatus;
import com.devtrack.repository.JobApplicationRepository;
import com.devtrack.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDate;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
@Service
public class JobApplicationService {
 private final JobApplicationRepository jobs; private final UserRepository users;
 public JobApplicationService(JobApplicationRepository jobs, UserRepository users){this.jobs=jobs;this.users=users;}
 private AppUser user(String email){return users.findByEmailIgnoreCase(email).orElseThrow(()->new ResponseStatusException(HttpStatus.UNAUTHORIZED,"User not found"));}
 public List<JobResponse> list(String email){AppUser u=user(email);return jobs.findAllByUserIdOrderByAppliedDateDescCreatedAtDesc(u.getId()).stream().map(JobResponse::from).toList();}
 @Transactional public JobResponse create(String email, JobRequest r){AppUser u=user(email);JobApplication j=new JobApplication();apply(j,r);j.setUser(u);return JobResponse.from(jobs.save(j));}
 public JobResponse get(String email, Long id){return JobResponse.from(owned(email,id));}
 @Transactional public JobResponse update(String email,Long id,JobRequest r){JobApplication j=owned(email,id);apply(j,r);return JobResponse.from(jobs.save(j));}
 public void delete(String email,Long id){jobs.delete(owned(email,id));}
 public Map<String,Long> stats(String email){AppUser u=user(email);Map<String,Long> out=new java.util.LinkedHashMap<>();out.put("total",jobs.countByUserId(u.getId()));for(JobStatus s:JobStatus.values())out.put(s.name(),jobs.countByUserIdAndStatus(u.getId(),s));return out;}
 private JobApplication owned(String email,Long id){AppUser u=user(email);return jobs.findByIdAndUserId(id,u.getId()).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Job application not found"));}
 private void apply(JobApplication j,JobRequest r){j.setCompany(r.company().trim());j.setRole(r.role().trim());j.setLocation(blankToNull(r.location()));j.setJobUrl(blankToNull(r.jobUrl()));j.setStatus(r.status()==null?JobStatus.APPLIED:r.status());j.setAppliedDate(r.appliedDate()==null?LocalDate.now():r.appliedDate());j.setNotes(blankToNull(r.notes()));}
 private String blankToNull(String v){return v==null||v.isBlank()?null:v.trim();}
}
