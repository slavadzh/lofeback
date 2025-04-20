package com.example.Lofeback.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "team")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(unique = true, nullable = false)
    String name;

    String description;

    @ManyToMany(mappedBy = "teams", fetch = FetchType.EAGER)
    @Builder.Default
    @JsonIgnore
    Set<Profile> profiles = new HashSet<>();

    @OneToMany(mappedBy = "team")
    List<Product> products = new ArrayList<>();
}
