// dto/RegisterRequestDTO.java
package com.jobtracker.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RegisterRequestDTO {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
}