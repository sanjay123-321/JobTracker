package com.jobtracker.service;

import com.jobtracker.dto.JobApplicationResponseDTO;
import com.jobtracker.model.ApplicationStatus;
import com.jobtracker.model.JobApplication;
import com.jobtracker.model.User;
import com.jobtracker.repository.JobApplicationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JobApplicationServiceTest {

    @Mock
    private JobApplicationRepository jobApplicationRepository;

    @InjectMocks
    private JobApplicationService jobApplicationService;

    @Test
    void getJobApplicationById_returnsCorrectApplication_whenOwnedByUser() {
        User user = new User();
        user.setId(1L);

        JobApplication app = new JobApplication();
        app.setId(5L);
        app.setCompanyName("Google");
        app.setAppliedPosition("Backend Developer");
        app.setDateApplied(LocalDate.of(2026, 1, 15));
        app.setApplicationStatus(ApplicationStatus.APPLIED);
        app.setUserDetail(user);

        when(jobApplicationRepository.findById(5L)).thenReturn(Optional.of(app));

        JobApplicationResponseDTO result = jobApplicationService.getJobApplicationById(5L, user);

        assertEquals("Google", result.getCompanyName());
        assertEquals(ApplicationStatus.APPLIED, result.getApplicationStatus());
    }
}