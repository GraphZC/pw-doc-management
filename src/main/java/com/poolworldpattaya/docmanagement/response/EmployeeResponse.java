package com.poolworldpattaya.docmanagement.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponse {
    private UUID id;
    private String username;
    private String name;
    private String roles;
}
