package com.example.Lofeback.dto;

import com.example.Lofeback.enums.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProfileDTO {
    String username;
    String login;
    String password;
    Role role;
    Set<Long> teamIds;

}
