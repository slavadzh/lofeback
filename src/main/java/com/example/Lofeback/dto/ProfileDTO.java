package com.example.Lofeback.dto;

import lombok.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ProfileDTO {
    private String username;
    private String login;
    private String password;
    private Set<Long> teamIds;
}
