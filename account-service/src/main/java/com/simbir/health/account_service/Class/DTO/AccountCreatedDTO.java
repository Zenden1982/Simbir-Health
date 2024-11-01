package com.simbir.health.account_service.Class.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountCreatedDTO {

    private String id;
    private String firstName;

    private String lastName;

    private String username;

    private String password;
}
