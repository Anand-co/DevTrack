package com.devtrack.controller;
import com.devtrack.service.JobApplicationService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
@RestController @RequestMapping("/api/dashboard")
public class DashboardController {
 private final JobApplicationService service; public DashboardController(JobApplicationService service){this.service=service;}
 @GetMapping("/stats") public Map<String,Long> stats(Authentication a){return service.stats(a.getName());}
}
