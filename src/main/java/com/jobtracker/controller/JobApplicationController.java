package com.jobtracker.controller;


import com.jobtracker.dto.JobApplicationRequestDTO;
import com.jobtracker.dto.JobApplicationResponseDTO;
import com.jobtracker.model.JobApplication;
import com.jobtracker.model.User;
import com.jobtracker.service.JobApplicationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class JobApplicationController {

    private final JobApplicationService jobApplicationService;

    public JobApplicationController(JobApplicationService jobApplicationService){
        this.jobApplicationService = jobApplicationService;
    }

    @GetMapping("/{id}")
    public JobApplicationResponseDTO getById(@PathVariable Long id){
        User fakeUser = new User();
        fakeUser.setId(1L);
        return jobApplicationService.getJobApplicationById(id,fakeUser);
    }

    @GetMapping
    public List<JobApplicationResponseDTO> getAll(){
        User fakeUser = new User();
        fakeUser.setId(1L);
        return jobApplicationService.getAllForUser(fakeUser);
    }

    @PostMapping
    public JobApplicationResponseDTO create(@RequestBody JobApplicationRequestDTO newApp){
        User fakeUser = new User();
        fakeUser.setId(1L);
        return jobApplicationService.createJobApplication(newApp,fakeUser);
    }

    @PutMapping("/{id}")
    public JobApplicationResponseDTO update(@PathVariable Long id,@RequestBody JobApplicationRequestDTO updatedApp){
        User fakeUser = new User();
        fakeUser.setId(1L);
        return jobApplicationService.updateJobApplication(id,updatedApp,fakeUser);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        User fakeUser = new User();
        fakeUser.setId(1L);
        jobApplicationService.deleteJobApplication(id,fakeUser);
    }
}
