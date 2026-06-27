package com.jobtracker.service;

import com.jobtracker.exception.ApplicationNotFoundException;
import com.jobtracker.exception.UnauthorizedAccessException;
import com.jobtracker.model.JobApplication;
import com.jobtracker.model.User;
import com.jobtracker.repository.JobApplicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobApplicationService {

    private final JobApplicationRepository jobApplicationRepository;

    public JobApplicationService(JobApplicationRepository jobApplicationRepository){
        this.jobApplicationRepository = jobApplicationRepository;
    }

    public JobApplication getJobApplicationById(Long id, User currentUser){
        JobApplication app = jobApplicationRepository.findById(id).orElseThrow(() -> new ApplicationNotFoundException("Application not found"));

        if(!app.getUserDetail().getId().equals(currentUser.getId())){
            throw new UnauthorizedAccessException("This application does not belong to you");
        }
        return app;
    }

    public List<JobApplication> getAllForUser(User currentUser){
        return jobApplicationRepository.findByUserDetail(currentUser);
    }

    public JobApplication createJobApplication(JobApplication newApp, User currentUser){
        newApp.setUserDetail(currentUser);
        return jobApplicationRepository.save(newApp);
    }
}
