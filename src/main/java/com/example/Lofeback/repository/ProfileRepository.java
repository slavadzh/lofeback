package com.example.Lofeback.repository;

import com.example.Lofeback.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Optional<Profile> findByLogin(String login);
}
