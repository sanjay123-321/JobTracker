// dto/LoginResponseDTO.java
package com.jobtracker.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class LoginResponseDTO {
    private String token;
}