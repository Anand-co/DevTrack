package com.devtrack.controller;
import com.devtrack.dto.JobRequest;
import com.devtrack.dto.JobResponse;
import com.devtrack.service.JobApplicationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController @RequestMapping("/api/jobs")
public class JobApplicationController {
 private final JobApplicationService service; public JobApplicationController(JobApplicationService service){this.service=service;}
 @GetMapping public List<JobResponse> list(Authentication a){return service.list(a.getName());}
 @PostMapping @ResponseStatus(HttpStatus.CREATED) public JobResponse create(Authentication a,@Valid @RequestBody JobRequest r){return service.create(a.getName(),r);}
 @GetMapping("/{id}") public JobResponse get(Authentication a,@PathVariable Long id){return service.get(a.getName(),id);}
 @PutMapping("/{id}") public JobResponse update(Authentication a,@PathVariable Long id,@Valid @RequestBody JobRequest r){return service.update(a.getName(),id,r);}
 @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(Authentication a,@PathVariable Long id){service.delete(a.getName(),id);}
}
