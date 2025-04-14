package com.example.Lofeback.service;

import com.example.Lofeback.repository.ProfileRepository;
import com.example.Lofeback.security.ProfileDetails;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProfileDetailsService implements UserDetailsService {
    private final ProfileRepository profileRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return profileRepository.findByLogin(login)
                .map(ProfileDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with login: " + login));
    }
}