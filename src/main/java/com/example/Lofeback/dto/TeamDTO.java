package com.example.Lofeback.dto;

import lombok.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TeamDTO {
    private String name;
    private String description;
    private Set<Long> profileIds;
}
