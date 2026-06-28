package com.jobtracker.service;

import com.jobtracker.dto.JobApplicationRequestDTO;
import com.jobtracker.dto.JobApplicationResponseDTO;
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

    public JobApplicationResponseDTO getJobApplicationById(Long id, User currentUser){
        JobApplication app = jobApplicationRepository.findById(id).orElseThrow(() -> new ApplicationNotFoundException("Application not found"));

        if(!app.getUserDetail().getId().equals(currentUser.getId())){
            throw new UnauthorizedAccessException("This application does not belong to you");
        }
        return toDTO(app);
    }

    public List<JobApplicationResponseDTO> getAllForUser(User currentUser){
        return jobApplicationRepository.findByUserDetail(currentUser).stream().map(this::toDTO).toList();
    }

    public JobApplicationResponseDTO createJobApplication(JobApplicationRequestDTO requestDTO, User currentUser){
        JobApplication newApp = new JobApplication();
        newApp.setCompanyName(requestDTO.getCompanyName());
        newApp.setAppliedPosition(requestDTO.getAppliedPosition());
        newApp.setDateApplied(requestDTO.getDateApplied());
        newApp.setApplicationStatus(requestDTO.getApplicationStatus());
        newApp.setUserDetail(currentUser);

        JobApplication saved = jobApplicationRepository.save(newApp);
        return toDTO(saved);
    }

    public JobApplicationResponseDTO updateJobApplication(Long id, JobApplicationRequestDTO requestDTO, User currentUser){
        JobApplication existing = jobApplicationRepository.findById(id)
                .orElseThrow(() -> new ApplicationNotFoundException("Application not found"));

        if (!existing.getUserDetail().getId().equals(currentUser.getId())) {
            throw new UnauthorizedAccessException("This application does not belong to you");
        }

        existing.setCompanyName(requestDTO.getCompanyName());
        existing.setAppliedPosition(requestDTO.getAppliedPosition());
        existing.setDateApplied(requestDTO.getDateApplied());
        existing.setApplicationStatus(requestDTO.getApplicationStatus());

        JobApplication saved = jobApplicationRepository.save(existing);
        return toDTO(saved);
    }

    public void deleteJobApplication(Long id, User currentUser){
        JobApplication existing = jobApplicationRepository.findById(id)
                .orElseThrow(() -> new ApplicationNotFoundException("Application not found"));

        if (!existing.getUserDetail().getId().equals(currentUser.getId())) {
            throw new UnauthorizedAccessException("This application does not belong to you");
        }

        jobApplicationRepository.delete(existing);
    }

    private JobApplicationResponseDTO toDTO(JobApplication app) {
        return new JobApplicationResponseDTO(
                app.getId(),
                app.getCompanyName(),
                app.getAppliedPosition(),
                app.getDateApplied(),
                app.getApplicationStatus()
        );
    }
}
