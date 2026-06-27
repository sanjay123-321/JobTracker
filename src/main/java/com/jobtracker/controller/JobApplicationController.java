package com.jobtracker.controller;


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
    public JobApplication getById(@PathVariable Long id){
        User fakeUser = new User();
        fakeUser.setId(1L);
        return jobApplicationService.getJobApplicationById(id,fakeUser);
    }

    @GetMapping
    public List<JobApplication> getAll(){
        User fakeUser = new User();
        fakeUser.setId(1L);
        return jobApplicationService.getAllForUser(fakeUser);
    }

    @PostMapping
    public JobApplication create(@RequestBody JobApplication newApp){
        User fakeUser = new User();
        fakeUser.setId(1L);
        return jobApplicationService.createJobApplication(newApp,fakeUser);
    }

    @PutMapping("/{id}")
    public JobApplication update(@PathVariable Long id,@RequestBody JobApplication updatedApp){
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
