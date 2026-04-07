package com.algotracker.AlgotrackerProject.dto;

import com.algotracker.AlgotrackerProject.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
    private Long userId;
    private String email;
    private String name;
    private Role role;

}
