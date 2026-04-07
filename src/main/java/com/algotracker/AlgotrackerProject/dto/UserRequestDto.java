package com.algotracker.AlgotrackerProject.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {

    @Email(message = "Invalid Email format")
    @NotNull(message = "Email is required")
    private String email;

    @NotBlank(message = "Name is required")
    private String name;

    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;
}
