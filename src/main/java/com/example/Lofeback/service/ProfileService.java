package com.example.Lofeback.service;

import com.example.Lofeback.dto.ProfileDTO;
import com.example.Lofeback.dto.TeamDTO;
import com.example.Lofeback.entity.Profile;
import com.example.Lofeback.entity.Team;
import com.example.Lofeback.repository.ProfileRepository;
import com.example.Lofeback.repository.TeamRepository;
import com.example.Lofeback.security.ProfileDetails;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final TeamRepository teamRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;


    @Transactional
    public void save(ProfileDTO profileDTO) {
        profileRepository.save(toEntity(profileDTO));
    }

    public List<ProfileDTO> getAll() {
        return profileRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    public Profile getById(Long id) {
        return profileRepository.findById(id).orElse(null);
    }

    @Transactional
    public void addTeam(Long teamId) {
        var team = teamRepository.getById(teamId);
        Profile profile = getAuthProfile();
        profile.getTeams().add(team);
        profileRepository.save(profile);
    }

    @Transactional
    public void update(long id, ProfileDTO profileDTO) {
        var profile = getById(id);
        profile.setUsername(profileDTO.getUsername());
        profile.setLogin(profileDTO.getLogin());
        profile.setPassword(profileDTO.getPassword());
        profileRepository.save(profile);
    }

    @Transactional
    public void delete(long id) {
        profileRepository.deleteById(id);
    }

    @Transactional
    public void register(ProfileDTO profileDTO) {
        var profile = toEntity(profileDTO);
        profile.setPassword(passwordEncoder.encode(profile.getPassword()));
        profileRepository.save(profile);
    }

    public Profile getAuthProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ProfileDetails profileDetails = (ProfileDetails) authentication.getPrincipal();
        return profileDetails.getProfile();
    }

    public ProfileDTO getLK() {
        return toDto(getAuthProfile());
    }

    private Profile toEntity(ProfileDTO profileDTO) {
        Profile profile = modelMapper.map(profileDTO, Profile.class);
        if (profileDTO.getTeamIds() != null) {
            Set<Team> teams = new HashSet<>(teamRepository.findAllById(profileDTO.getTeamIds()));
            profile.setTeams(teams);
        }
        return profile;
    }

    private ProfileDTO toDto(Profile profile) {
        ProfileDTO dto = modelMapper.map(profile, ProfileDTO.class);
        Set<Long> teamIds = profile.getTeams().stream()
                .map(Team::getId)
                .collect(Collectors.toSet());
        dto.setTeamIds(teamIds);
        return dto;
    }
}
