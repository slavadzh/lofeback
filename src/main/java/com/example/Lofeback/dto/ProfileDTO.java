package com.example.Lofeback.dto;

import com.example.Lofeback.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
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

    @Size(min = 2, max = 50, message = "Никнейм от 2 до 50")
    String username;

    @Email
    String login;

    @Size(min = 2, max = 50, message = "Пароль от 2 до 50")
    String password;
    Role role;
    Set<Long> teamIds;

}
