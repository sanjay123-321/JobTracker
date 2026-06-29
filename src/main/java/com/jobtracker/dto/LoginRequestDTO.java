// dto/LoginRequestDTO.java
package com.jobtracker.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LoginRequestDTO {
    private String email;
    private String password;
}