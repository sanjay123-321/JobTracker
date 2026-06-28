// dto/JobApplicationResponseDTO.java
package com.jobtracker.dto;

import com.jobtracker.model.ApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter @AllArgsConstructor
public class JobApplicationResponseDTO {
    private Long id;
    private String companyName;
    private String appliedPosition;
    private LocalDate dateApplied;
    private ApplicationStatus applicationStatus;
}