package com.simbir.health.hospital_service.Class.DTO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;

    private String lastName;

    private String firstName;

    private String username;

    private List<String> roles;
}
