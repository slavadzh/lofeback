package com.example.Lofeback.entity;

import com.example.Lofeback.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "profile")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(unique = true, nullable = false)
    String username;

    @Column(unique = true, nullable = false)
    String login;

    @Column(unique = true, nullable = false)
    String password;

    @Enumerated(EnumType.STRING)
    Role role;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "profile_team",
            joinColumns =  @JoinColumn(name = "profile_id"),
            inverseJoinColumns = @JoinColumn(name = "team_id")

    )
    @Builder.Default
    @JsonIgnore
    Set<Team> teams = new HashSet<>();
}
