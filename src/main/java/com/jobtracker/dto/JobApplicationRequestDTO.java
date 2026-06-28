// dto/JobApplicationRequestDTO.java
package com.jobtracker.dto;

import com.jobtracker.model.ApplicationStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class JobApplicationRequestDTO {
    private String companyName;
    private String appliedPosition;
    private LocalDate dateApplied;
    private ApplicationStatus applicationStatus;
}