package com.jobtracker.controller;


import com.jobtracker.dto.JobApplicationRequestDTO;
import com.jobtracker.dto.JobApplicationResponseDTO;
import com.jobtracker.model.User;
import com.jobtracker.service.JobApplicationService;
import com.jobtracker.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class JobApplicationController {

    private final JobApplicationService jobApplicationService;
    private final UserService userService;

    public JobApplicationController(JobApplicationService jobApplicationService, UserService userService){
        this.jobApplicationService = jobApplicationService;
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public JobApplicationResponseDTO getById(@PathVariable Long id) {
        User currentUser = userService.getCurrentUser();
        return jobApplicationService.getJobApplicationById(id, currentUser);
    }

    @GetMapping
    public List<JobApplicationResponseDTO> getAll(){
        User currentUser = userService.getCurrentUser();
        return jobApplicationService.getAllForUser(currentUser);
    }

    @PostMapping
    public JobApplicationResponseDTO create(@RequestBody JobApplicationRequestDTO newApp){
        User currentUser = userService.getCurrentUser();
        return jobApplicationService.createJobApplication(newApp,currentUser);
    }

    @PutMapping("/{id}")
    public JobApplicationResponseDTO update(@PathVariable Long id,@RequestBody JobApplicationRequestDTO updatedApp){
        User currentUser = userService.getCurrentUser();
        return jobApplicationService.updateJobApplication(id,updatedApp,currentUser);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        User currentUser = userService.getCurrentUser();
        jobApplicationService.deleteJobApplication(id,currentUser);
    }
}
